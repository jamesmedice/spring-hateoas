package com.open.demo.hateoas;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * 
 * @author TOSS
 *
 */
@SpringBootApplication
public class Application {

    private static final String USING_EXTERNAL_DATABASE = "Using external database";

    private final static Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
	final ApplicationContext context = SpringApplication.run(Application.class, args);
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
	LOG.info(USING_EXTERNAL_DATABASE);
	return DataSourceBuilder.create().build();
    }
}
