package com.websystique.springmvc.configuration;

import com.websystique.springmvc.utils.Utils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan({"com.websystique.springmvc.configuration"})
@PropertySource(value = {"classpath:application.properties"})
public class HibernateConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        String u = new Utils("HibernateConf", "sessionFactory").toString();
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
                /*FactoryBean that creates a Hibernate SessionFactory.
                This is the usual way to set up a shared Hibernate SessionFactory in a Spring application context;
                the SessionFactory can then be passed to Hibernate-based data access objects via dependency injection.*/
        sessionFactory.setDataSource(dataSource());
        //Set the DataSource to be used by the SessionFactory.
        sessionFactory.setPackagesToScan(new String[]{"com.websystique.springmvc.model"});
                /*Specify packages to search for autodetection of your entity classes in the classpath.
                This is analogous to Spring's component-scan feature */

        sessionFactory.setHibernateProperties(hibernateProperties());
                /*Set Hibernate properties, such as "hibernate.dialect".
                Note: Do not specify a transaction provider here when using Spring-driven transactions.
                It is also advisable to omit connection provider settings and use a Spring-set DataSource instead.*/
        return sessionFactory;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    public DataSource dataSource() {
        String u = new Utils("HibernateConf", "dataSource").toString();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        /*returning a new Connection from every getConnection call*/
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }

    private Properties hibernateProperties() {
        String u = new Utils("HibernateConf", "hibernateProp").toString();
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        return properties;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
        String u = new Utils("HibernateConf", "transactionManager").toString();
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(s);
        return txManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }


}
