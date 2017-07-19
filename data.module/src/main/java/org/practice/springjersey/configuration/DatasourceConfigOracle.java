//package org.practice.springjersey.configuration;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import java.text.MessageFormat;
//
//
//@Slf4j
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        basePackages = {"org.practice.springjersey.repository"},
//        entityManagerFactoryRef = "entityManagerFactory",
//        transactionManagerRef = "transactionManager"
//    )
//public class DatasourceConfigOracle {
//
//    public static final String[] ENTITY_PATH =
//            {
//                    "org.practice.springjersey.domain"
//            };
//
//    @Bean
//    public PlatformTransactionManager transactionManager(){
//        return new JpaTransactionManager(entityManagerFactory().getObject());
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        return getFactoryBean(oracleDataSource(), "SpringBootJersey", ENTITY_PATH);
//    }
//
//    @Bean
//    public DataSource oracleDataSource() {
//        log.info("Creating {} datasource for entities {}", this.getClass().getSimpleName(), ENTITY_PATH);
//        return jndiLookup("java:/jdbc/EVENT_DEVCLOUD_DS");
//    }
//
//    DataSource jndiLookup(String jndiName) {
//        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
//        dsLookup.setResourceRef(true);
//        log.info("Using datasource with jndi Name: {}", jndiName);
//        return dsLookup.getDataSource(jndiName);
//    }
//
//    LocalContainerEntityManagerFactoryBean getFactoryBean(DataSource dataSource, String persistenceUnitName, String... packages) {
//
//        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
//        jpaVendorAdapter.setGenerateDdl(isGeneratingDdl());
//        jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.Oracle10gDialect");
//
//        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
//
//        factoryBean.setPersistenceUnitName(MessageFormat.format("PUN-{0}", persistenceUnitName));
//        factoryBean.setDataSource(dataSource);
//        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
//        factoryBean.setPackagesToScan(packages);
////        factoryBean.setJtaDataSource(dataSource);
//        return factoryBean;
//    }
//
//    boolean isGeneratingDdl() {
//        return false;
//    }
//}
