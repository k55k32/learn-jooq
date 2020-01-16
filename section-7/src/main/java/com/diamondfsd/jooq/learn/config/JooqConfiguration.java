package com.diamondfsd.jooq.learn.config;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

import javax.sql.DataSource;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Diamond
 */
@Configuration
@Import(DataSourceConfig.class)
public class JooqConfiguration {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public org.jooq.Configuration configuration(
            @Autowired
            @Qualifier("learnJooqDataSource")
                    DataSource learnJooqDataSource,
            @Autowired
            @Qualifier("learnJooq2DataSource")
                    DataSource learnJooq2DataSource,
            InjectionPoint injectionPoint) {
        AnnotatedElement annotatedElement = injectionPoint.getAnnotatedElement();
        if (Constructor.class.isAssignableFrom(annotatedElement.getClass())) {
            DataSource targetDataSource;
            Class declaringClass = ((Constructor) annotatedElement).getDeclaringClass();
            String packageName = declaringClass.getPackage().getName();
            switch (packageName) {
                case "com.diamondfsd.jooq.learn.codegen.learn_jooq.tables.daos":
                    targetDataSource = learnJooqDataSource;
                    break;
                case "com.diamondfsd.jooq.learn.codegen.learn_jooq_2.tables.daos":
                    targetDataSource = learnJooq2DataSource;
                    break;
                default:
                    throw new NoSuchBeanDefinitionException("no target switch");
            }
            return createOrGetConfiguration(targetDataSource, packageName);
        }
        throw new NoSuchBeanDefinitionException("no target switch");
    }

    Map<String, org.jooq.Configuration> configurationMap = new HashMap<>();
    private org.jooq.Configuration createOrGetConfiguration(DataSource targetDataSource, String packageName) {
        return configurationMap.computeIfAbsent(packageName, (k) -> {
            org.jooq.Configuration configuration = new DefaultConfiguration();
            configuration.set(SQLDialect.MYSQL);
            configuration.set(targetDataSource);
            configuration.settings().setRenderSchema(false);
            return configuration;
        });
    }
}
