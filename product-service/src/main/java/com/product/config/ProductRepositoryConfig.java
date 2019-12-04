package com.product.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "productEntityManagerFactory",
        basePackages = {"com.product"},
        transactionManagerRef = "productTransactionManager")
public class ProductRepositoryConfig {

    @Primary
    @Bean
    @ConfigurationProperties("app.datasource.product")
    public DataSourceProperties productDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Primary
    @Bean
    @ConfigurationProperties("app.datasource.product.configuration")
    public DataSource productDataSource() {
        return productDataSourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean productEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(productDataSource())
                .packages("com.product.domain")
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager productTransactionManager(final @Qualifier("productEntityManagerFactory") LocalContainerEntityManagerFactoryBean productEntityManagerFactory) {
        return new JpaTransactionManager(productEntityManagerFactory.getObject());
    }
}
