package no.niths.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "no.niths" }) //Change to DAO layer
//@ImportResource( "classpath*:*springDataConfig.xml" )
public class PersistenceHibernateConfig{
	
	@Value( "${jdbc.driver.class_name}" )
	private String driverClassName;

	@Value( "${jdbc.url}" )
	private String url;

	@Value("${jdbc.username}")
	private String username;
	
	@Value("${jdbc.password}")
	private String password;
	
	@Value( "${hibernate.dialect}" )
	private String hibernateDialect;
	
	@Value( "${hibernate.hbm2ddl.auto}" )
	private String hibernateHbm2ddlAuto;

	@Value( "${hibernate.show_sql}" )
	boolean hibernateShowSql;

	

	
	
	//Add properties here, show sql and so on
	final Properties additionlProperties(){
		Properties prop = new Properties();
		prop.setProperty("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);
		
		return prop;
	}
	

   @Bean
   public org.springframework.orm.hibernate4.LocalSessionFactoryBean alertsSessionFactory(){
		org.springframework.orm.hibernate4.LocalSessionFactoryBean sessionFactory = new org.springframework.orm.hibernate4.LocalSessionFactoryBean();
      sessionFactory.setDataSource( this.restDataSource() );
      sessionFactory.setPackagesToScan( new String[ ] { "no.niths" } );
      sessionFactory.setHibernateProperties( this.additionlProperties() );
     
      
      return sessionFactory;
   }
   @Bean
   public DataSource restDataSource(){
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName( this.driverClassName );
      dataSource.setUrl( this.url );
      dataSource.setUsername( this.username );
      dataSource.setPassword( this.password );
      
      return dataSource;
   }
   
   @Bean
   public HibernateTransactionManager transactionManager(){
      HibernateTransactionManager txManager = new HibernateTransactionManager();
      txManager.setSessionFactory( this.alertsSessionFactory().getObject() );
      
      return txManager;
   }
   
//   @Bean
//   public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
//      return new PersistenceExceptionTranslationPostProcessor();
//   }

}
