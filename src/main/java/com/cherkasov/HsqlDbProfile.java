package com.cherkasov;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

//@Log4j2
@Configuration
@Profile("dev")
@PropertySource(value = {"classpath:hsql.properties"})
public class HsqlDbProfile  {
//  @Bean
//  @Autowired
  public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
    ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
    resourceDatabasePopulator.setContinueOnError(true);
    resourceDatabasePopulator.setSqlScriptEncoding("UTF-8");

//    resourceDatabasePopulator.addScript(new ClassPathResource("db/hsql_schema.sql"));
//    resourceDatabasePopulator.addScript(new ClassPathResource("db/populateDB.sql"));


    DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
    dataSourceInitializer.setDataSource(dataSource);
    dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);

//    log.info("Database Populated with profile DEV");
    return dataSourceInitializer;
  }
}
