package org.eauction.axon.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
//@EnableJpaRepositories(basePackages = "org.eauction.repos",entityManagerFactoryRef = "pocEntityMgrFactory")
@EntityScan("org.eauction.entity")
public class AxonConfig {

  private final Environment env;

  public AxonConfig(final Environment env) {
      this.env = env;
  }

 
  @Bean(name = "pocEntityMgrFactory")
  LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
                                                              Environment env) {
      LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
      entityManagerFactoryBean.setDataSource(dataSource);
      entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
      entityManagerFactoryBean.setPackagesToScan("org.eauction");

      Properties jpaProperties = new Properties();
   
      jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
    jpaProperties.put("hibernate.dialect", env.getProperty("poc.jpa.hibernet.dialect"));
//      jpaProperties.put("hibernate.dialect", env.getProperty("poc.jpa.hibernate.dialect"));
      jpaProperties.put("hibernate.show_sql", "false"   );
      jpaProperties.put("hibernate.format_sql", "true");

      entityManagerFactoryBean.setJpaProperties(jpaProperties);
      entityManagerFactoryBean.setPackagesToScan(
                      "org.eauction.entity",
                      "org.axonframework.eventsourcing.eventstore.jpa",
                      "org.axonframework.modelling.saga.repository.jpa",
                      "org.axonframework.eventhandling.tokenstore.jpa");
     

      return entityManagerFactoryBean;
  }
 
  @Primary
  @Bean(name = "pocDataSource")
  @ConfigurationProperties(prefix = "spring.datasource")
  public DataSource pocDataSource() {
      return DataSourceBuilder.create().build();
  }

}
