package no.niths.infrastructure;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractHibernateDAO< T extends Serializable > {
	   
	   private Class< T > clazz;
	   
	   @Autowired
	   SessionFactory sessionFactory;
	   
	   public void setClazz( final Class< T > clazzToSet ){
	      this.clazz = clazzToSet;
	   }
	   
	   public T findOne( final Long id ){
	      return (T) this.getCurrentSession().get( this.clazz, id );
	   }
	   public List< T > findAll(){
	      return this.getCurrentSession()
	       .createQuery( "from " + this.clazz.getName() ).list();
	   }
	   
	   public void save( final T entity ){
	      this.getCurrentSession().persist( entity );
	   }
	   
	   public void update( final T entity ){
	      this.getCurrentSession().merge( entity );
	   }
	   
	   public void delete( final T entity ){
	      this.getCurrentSession().delete( entity );
	   }
	   public void deleteById( final Long entityId ){
	      final T entity = this.findOne( entityId );
	      
	      this.delete( entity );
	   }
	   
	   protected Session getCurrentSession(){
	      return this.sessionFactory.getCurrentSession();
	   }
	}