package com.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "creditEntityManagerFactory",
        basePackages = {"com.repository.credit"},
        transactionManagerRef = "creditTransactionManager"
)
public class CreditRepositoryConfig {

    @Bean
    @ConfigurationProperties("app.datasource.credit")
    public DataSourceProperties creditDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("app.datasource.credit.configuration")
    public DataSource creditDataSource() {
        return creditDataSourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean(name = "creditEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean creditEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(creditDataSource())
                .packages("com.domain.credit")
                .build();
    }

    @Bean
    public PlatformTransactionManager creditTransactionManager(final @Qualifier("creditEntityManagerFactory") LocalContainerEntityManagerFactoryBean creditEntityManagerFactory) {
        return new JpaTransactionManager(creditEntityManagerFactory.getObject());
    }
}
