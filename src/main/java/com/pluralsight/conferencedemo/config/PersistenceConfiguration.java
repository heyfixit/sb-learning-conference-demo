package com.pluralsight.conferencedemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

// "Java Configuration" method

// this configuration class can customize and create changes or configuration in our persistence tier
@Configuration // spring needs to know this is a configuration
public class PersistenceConfiguration {
    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    // any methods that are defined here can provide Bean definitions that will be used in the
    // spring context

    // this is how to override the datasource being provided by the spring JPA starter

    // when this method returns the datasource object, spring looks for it and tries to see if one
    // already exists in the Spring context, if it does, spring will replace it with this one

    // Spring needs to know it's a Spring Bean via the @Bean annotation
//    @Bean
//    public DataSource dataSource() {
//        // where config actually happens in Java config class
//        DataSourceBuilder<? extends DataSource> builder = DataSourceBuilder.create();
//        builder.url("jdbc:postgresql://localhost:3000/conference_app");
//        builder.username(dbUsername);
//        builder.password(dbPassword);
//        System.out.println("My custom datasource bean has bean initialized and set");
//        return builder.build();
//    }
}
