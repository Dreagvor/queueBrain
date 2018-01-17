package com.zoxal.queuebrain.config;

import com.jolbox.bonecp.BoneCPDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Insert description vere
 *
 * @author Mike
 * @version 01/08/2018
 */
@Configuration
@EnableJpaRepositories("com.zoxal.queuebrain.repository")
@EnableTransactionManagement
@PropertySource(value = "classpath:db-credentials.properties")
public class DataConfig {
    private static final Logger logger = LoggerFactory.getLogger(DataConfig.class);

    private final Environment env;

    @Autowired
    public DataConfig(Environment env) {
        logger.debug("Instantiating data config");
        this.env = env;
    }

    @Bean
    public BoneCPDataSource boneCPDataSource() {
        logger.debug("driver: {}", env.getProperty("jdbc.driver"));
        logger.debug("url: {}", env.getProperty("jdbc.url"));
        logger.debug("username: {}", env.getProperty("jdbc.username"));
        logger.debug("password: {}", env.getProperty("jdbc.password"));
        BoneCPDataSource boneCPDataSource = new BoneCPDataSource();
        boneCPDataSource.setDriverClass(env.getProperty("jdbc.driver"));
        boneCPDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
        boneCPDataSource.setUsername(env.getProperty("jdbc.username"));
        boneCPDataSource.setPassword(env.getProperty("jdbc.password"));
        boneCPDataSource.setIdleConnectionTestPeriodInMinutes(60);
        boneCPDataSource.setIdleMaxAgeInMinutes(420);
        boneCPDataSource.setMaxConnectionsPerPartition(20);
        boneCPDataSource.setMinConnectionsPerPartition(5);
        boneCPDataSource.setPartitionCount(1);
        boneCPDataSource.setAcquireIncrement(5);
        boneCPDataSource.setStatementsCacheSize(100);
        boneCPDataSource.setReleaseHelperThreads(3);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection manager = DriverManager.getConnection(
                    "jdbc:mysql://23.99.115.175:3306/queuebraindb", "qb-admin", "password+1P");
            manager.close();
        } catch (Exception e) {
            logger.warn("Failed with bare jdbc");
        }

        try {
            boneCPDataSource.getConnection();
        } catch (Exception e) {
            logger.warn("Failed with boneCP");
        }

        return boneCPDataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(false);
        vendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5InnoDBDialect");
        vendorAdapter.setDatabase(Database.MYSQL);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.zoxal.queuebrain.model");
        factory.setDataSource(dataSource);

        Properties properties = new Properties();
//        properties.setProperty("spring.jpa.hibernate.ddl-auto", "create-drop");
        properties.setProperty("spring.jpa.generate-ddl", "true");
//        properties.setProperty("hibernate.cache.use_second_level_cache", "true");
//        properties.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
//        properties.setProperty("hibernate.cache.use_query_cache", "true");
//        properties.setProperty("hibernate.generate_statistics", "true");

        factory.setJpaProperties(properties);

        factory.afterPropertiesSet();

        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }
}
