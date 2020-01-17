package com.diamondfsd.jooq.learn.config;

import org.jooq.SQLDialect;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.util.Map;

/**
 * @author Diamond
 */
@Configuration
@Import(DataSourceConfig.class)
public class JooqConfiguration {

    @Bean
    @Primary
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public org.jooq.Configuration configuration(
            Map<String, DefaultConfiguration> configurationMap,
            InjectionPoint injectionPoint) {
        AnnotatedElement annotatedElement = injectionPoint.getAnnotatedElement();
        if (Constructor.class.isAssignableFrom(annotatedElement.getClass())) {
            Class declaringClass = ((Constructor) annotatedElement).getDeclaringClass();
            String packageName = declaringClass.getPackage().getName();
            org.jooq.Configuration configuration;
            switch (packageName) {
                case "com.diamondfsd.jooq.learn.codegen.learn_jooq.tables.daos":
                    configuration = configurationMap.get("learnJooqConfiguration");
                    break;
                case "com.diamondfsd.jooq.learn.codegen.learn_jooq_2.tables.daos":
                    configuration = configurationMap.get("learnJooq2Configuration");
                    break;
                default:
                    throw new NoSuchBeanDefinitionException("no target switch");
            }
            return configuration;
        }
        throw new NoSuchBeanDefinitionException("no target switch");
    }

    @Bean
    public DefaultConfiguration learnJooqConfiguration(@Autowired
                                                       @Qualifier("learnJooqDataSource")
                                                               DataSource learnJooqDataSource) {
        DefaultConfiguration configuration = new DefaultConfiguration();
        configuration.set(SQLDialect.MYSQL);
        configuration.set(learnJooqDataSource);
        configuration.settings().setRenderSchema(false);
        return configuration;
    }

    @Bean
    public DefaultConfiguration learnJooq2Configuration(@Autowired
                                                        @Qualifier("learnJooq2DataSource")
                                                                DataSource learnJooq2DataSource) {
        DefaultConfiguration configuration = new DefaultConfiguration();
        configuration.set(SQLDialect.MYSQL);
        configuration.set(learnJooq2DataSource);
        configuration.settings().setRenderSchema(false);
        return configuration;
    }
}
