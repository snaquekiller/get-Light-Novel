package getLn.configuration;

import java.util.Properties;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;

import com.zaxxer.hikari.HikariDataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import getln.data.Data;
import getln.data.entity.EntityData;
import getln.data.service.DataRepository;

/**
 * Base JPA configuration.
 */
@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "mainEntityManagerFactory", transactionManagerRef = "mainTransactionManager", basePackageClasses = {
    EntityData.class, DataRepository.class})
public abstract class JpaConfiguration {

    /**
     * The env.
     */
    @Inject
    private Environment env;

    /**
     * Data source.
     *
     * @return The hikari data source.
     */
    @Bean
    public HikariDataSource dataSource() {
        final HikariDataSource ds = new HikariDataSource();
        ds.setMinimumIdle(env.getRequiredProperty("cp.minimumIdle", Integer.class));
        ds.setMaximumPoolSize(env.getRequiredProperty("cp.maximumPoolSize", Integer.class));
        ds.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        ds.addDataSourceProperty("url", env.getProperty("ds.url"));
        ds.addDataSourceProperty("user", env.getProperty("ds.user"));
        ds.addDataSourceProperty("password", env.getProperty("ds.password"));
        ds.addDataSourceProperty("cachePrepStmts", true);
        ds.addDataSourceProperty("prepStmtCacheSize", 250);
        ds.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
        ds.addDataSourceProperty("useServerPrepStmts", true);
        return ds;
    }

    /**
     * entity manager factory.
     *
     * @return The entity manager factory.
     */
    @Bean(name = "mainEntityManagerFactory")
    @Primary
    public EntityManagerFactory entityManagerFactory() {
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        final LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan(getPackagesToScan());
        factory.setDataSource(dataSource());
        factory.setJpaDialect(new HibernateJpaDialect());
        factory.setJpaProperties(jpaProperties());
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    /**
     * Returns the JPA properties.
     *
     * @return The JPA properties.
     */
    private Properties jpaProperties() {
        final Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty("hibernate.hbm2ddl.auto", "none");
        return properties;
    }

    /**
     * Transaction manager.
     *
     * @return The platform transaction manager.
     */
    @Bean(name = "mainTransactionManager")
    @Primary
    public PlatformTransactionManager transactionManager() {
        final JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }

    /**
     * Returns a translator that maps Hibernate exceptions to Spring data exceptions.
     *
     * @return The translator.
     */
    @Bean
    public PersistenceExceptionTranslator persistenceExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }

    private String[] getPackagesToScan() {
        return new String[]{Data.class.getPackage().getName()};
    }

}
