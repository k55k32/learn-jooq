package com.diamondfsd.jooq.learn.config;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

/**
 * @author Diamond
 */
@Configuration
@Import(DataSourceConfig.class)
public class JooqConfiguration {
    @Bean
    public DSLContext dslContext(org.jooq.Configuration configuration) {
        return DSL.using(configuration);
    }

    @Bean
    public org.jooq.Configuration configuration(DataSource dataSource) {
        org.jooq.Configuration configuration = new DefaultConfiguration();
        configuration.set(SQLDialect.MYSQL);
        configuration.set(dataSource);
        return configuration;
    }
}
