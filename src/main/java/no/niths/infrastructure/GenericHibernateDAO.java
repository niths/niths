package no.niths.infrastructure;

import java.io.Serializable;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope( BeanDefinition.SCOPE_PROTOTYPE )
public class GenericHibernateDAO< T extends Serializable > 
 extends AbstractHibernateDAO< T > implements IGenericDAO< T >{
   // 
}