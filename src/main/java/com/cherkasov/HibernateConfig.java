package com.cherkasov;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by hawk on 09.05.2016.
 */
@Configuration
@EnableTransactionManagement
//@Import({HsqlDbProfile.class, MysqlDbProfile.class})
//@Profile("dev")
//@PropertySource(value = {"classpath:db/hsql.properties"})
public class HibernateConfig {

    @Autowired
    private Environment environment;


    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.c3p0.minPoolSize", environment.getRequiredProperty("hibernate.c3p0.minPoolSize"));
        properties.put("hibernate.c3p0.maxPoolSize", environment.getRequiredProperty("hibernate.c3p0.maxPoolSize"));
        properties.put("hibernate.c3p0.timeout", environment.getRequiredProperty("hibernate.c3p0.timeout"));
        properties.put("hibernate.c3p0.max_statement", environment.getRequiredProperty("hibernate.c3p0.max_statement"));
        return properties;
    }


    @Bean
//    @Autowired
//    @Resource(name = "LocalContainerEntityManagerFactoryBean")
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {//EntityManagerFactory
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Bean
//    @Autowired
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factoryBean =
                new LocalContainerEntityManagerFactoryBean();

        factoryBean.setDataSource(dataSource);
        //scan for beans @Entity classes
        factoryBean.setPackagesToScan("com.cherkasov");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
        //vendorAdapter.setGenerateDdl(generateDdl)

        factoryBean.setJpaVendorAdapter(vendorAdapter);
        //if multi unit, then set name
//        factoryBean.setPersistenceUnitName("hsqlEntityManager");
        factoryBean.setJpaProperties(hibernateProperties());

        return factoryBean;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

}
