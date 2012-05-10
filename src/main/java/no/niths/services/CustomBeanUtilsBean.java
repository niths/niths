package no.niths.services;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtilsBean;
/**
 * 
 * The overwritten methods ignores null properties
 *
 */
public class CustomBeanUtilsBean extends BeanUtilsBean {

    @Override
    public void copyProperties(Object oldObject, Object newOBject)
            throws IllegalAccessException, InvocationTargetException {
        if (newOBject == null)
            return;
        super.copyProperties(oldObject, newOBject);
    }

    @Override
    public void copyProperty(Object dest, String name, Object value)
            throws IllegalAccessException, InvocationTargetException {
        if (value == null)
            return;
        super.copyProperty(dest, name, value);
    }
}