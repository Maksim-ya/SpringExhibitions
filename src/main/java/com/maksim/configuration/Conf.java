package com.maksim.configuration;


import com.maksim.model.hibernateImpl.UserDaoImpl;
import com.maksim.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan({"com.maksim.model", "com.maksim.service"})
@EnableTransactionManagement
@PropertySource(value = {"classpath: connection.properties"})
public class Conf {

    @Autowired
    private Environment environment;

    @Bean
    public UserDaoImpl userDao() {
        return new UserDaoImpl();
    }

    @Bean
    public UserService userService() {
        return new UserService();
    }

    @Bean
    public LocalSessionFactoryBean sessionFactoryBean() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[]{"com.maksim.model"});
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect",environment.getRequiredProperty("hibernate.dialect" ));
        properties.put("hibernate.show_sql",environment.getRequiredProperty("hibernate.show_sql" ));
        properties.put("hibernate.hbm2ddl.auto ",environment.getProperty("hibernate.hbm2ddl.auto" ));
        return properties;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }

    @Bean
    @Autowired
    public PlatformTransactionManager platformTransactionManager(LocalSessionFactoryBean sessionFactoryBean){
        HibernateTransactionManager platformTransactionManager = new HibernateTransactionManager();
        platformTransactionManager.setSessionFactory(sessionFactoryBean.getObject());
        // just because we using hibernate built-in datasource this property is set to false
        platformTransactionManager.setAutodetectDataSource(false);

        return platformTransactionManager;
    }

}
