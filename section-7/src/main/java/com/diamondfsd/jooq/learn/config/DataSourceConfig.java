package com.diamondfsd.jooq.learn.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author Diamond
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:jdbc.properties")
public class DataSourceConfig {

    public static final String TX_LEARN_JOOQ = "learnJooqTransactionManager";
    public static final String TX_LEARN_JOOQ_2 = "learnJooq2TransactionManager";

    @Bean
    public PlatformTransactionManager learnJooqTransactionManager(
            @Autowired
            @Qualifier("learnJooqDataSource")
                    DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public PlatformTransactionManager learnJooq2TransactionManager(
            @Autowired
            @Qualifier("learnJooq2DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public DataSource learnJooqDataSource(
            @Value("${datasource1.jdbc.url}")
                    String url,
            @Value("${datasource1.jdbc.username}")
                    String username,
            @Value("${datasource1.jdbc.password}")
                    String password) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        return new TransactionAwareDataSourceProxy(new HikariDataSource(config));
    }

    @Bean
    public DataSource learnJooq2DataSource(
            @Value("${datasource2.jdbc.url}")
                    String url,
            @Value("${datasource2.jdbc.username}")
                    String username,
            @Value("${datasource2.jdbc.password}")
                    String password) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        return new TransactionAwareDataSourceProxy(new HikariDataSource(config));
    }
}
