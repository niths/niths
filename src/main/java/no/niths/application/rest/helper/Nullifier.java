package no.niths.application.rest.helper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnore;

import no.niths.application.rest.lists.ListAdapter;
import no.niths.domain.Domain;

/**
 * 
 * @author NITHs
 *
 */
public class Nullifier<T> {

    private final Object[] varargsNull = new Object[] { null };

    public void clearR(ListAdapter<T> list) {
        for (Object domain: list) {
            for (Field field : domain.getClass().getDeclaredFields()) {
                Class<?> type = field.getType();

                if (Collection.class.isAssignableFrom(type) ||
                        Domain.class.isAssignableFrom(type)) {
                    try {

                        // Dynamically find the set method for collection types
                        Method method = domain.getClass().getMethod(
                                generateSetterName(field.getName()),
                                field.getType());

                        // Dynamically call the method and set any collection
                        // to null
                        method.invoke(domain, new Object[] { null });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void clearSubRelations(T domain) {
        Class<?> domainType = domain.getClass();

        outer:
        for (Field outerField : domainType.getDeclaredFields()) {
            Class<?> outerType         = outerField.getType();
            String outerFieldName = outerField.getName();

            try {

                // The current domain's (collection) child will yield true
                if (Collection.class.isAssignableFrom(outerType)) {

                    // If the annotations indicate they are transient, skip to
                    // the next attribute
                    if (checkAnnotations(outerField.getDeclaredAnnotations())) {
                        continue outer;
                    }

                    // Find the collection's getter method
                    Method outerMethod = domainType.getMethod(
                            generateGetterName(outerFieldName), // Name
                            (Class<?>[]) null); // Parameter(s)

                    Object result = outerMethod.invoke(domain);
                    if (result != null) {
                        for (Domain innerCollection :
                                (Collection<Domain>) result) {
                            removeChild(
                                    innerCollection,
                                    innerCollection.getClass());
                        }
                    }
                } else if (Domain.class.isAssignableFrom(outerType)) {

                    if (checkAnnotations(outerField.getAnnotations())) {

                        // Nullify domain that are transient
                        domainType.getDeclaredMethod(
                                generateSetterName(outerFieldName),
                                outerType)
                                    .invoke(domain, varargsNull);

                    // Nullify all domains and collections in the domain
                    } else {
                        Domain result = (Domain) domainType.getMethod(
                                generateGetterName(outerFieldName),
                                (Class<?>[]) null).invoke(domain);
                        
                        if (result != null) {
                            removeChild(
                                    result,
                                    result.getClass().getSuperclass());
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean checkAnnotations(Annotation[] annotations) {
        boolean isTransient = false;

        for (Annotation annotation : annotations) {
            Class<?> annotationType = annotation.annotationType();
            if (annotationType == JsonIgnore.class
                    || annotationType == XmlTransient.class) {
                isTransient = true;
            }
        }

        return isTransient;
    }

    private String generateGetterName(String fieldName) {
        return String.format("get%s", captialize(fieldName));
    }

    private String generateSetterName(String fieldName) {
        return String.format("set%s", captialize(fieldName));
    }

    private String captialize(String text) {
        return Character.toUpperCase(text.charAt(0)) + text.substring(1);
    }

    private void removeChild(Object target, Class<?> type)
            throws NoSuchMethodException, SecurityException,
                   IllegalAccessException, IllegalArgumentException,
                   InvocationTargetException {

        for (Field field : type.getDeclaredFields()) {
            Class<?> fieldType = field.getType();

            // Nullify any domain or collection
            if (Collection.class.isAssignableFrom(fieldType)
                    || Domain.class.isAssignableFrom(fieldType)) {
                type.getMethod(
                        generateSetterName(field.getName()),
                        fieldType).invoke(target, varargsNull);
            }
        }
    }
}