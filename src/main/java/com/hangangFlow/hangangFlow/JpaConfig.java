package com.hangangFlow.hangangFlow;

//import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = {
        "com.hangangFlow.hangangFlow.domain.user",
        "com.hangangFlow.hangangFlow.domain.park",
        "com.hangangFlow.hangangFlow.domain.bookmark",
        "com.hangangFlow.hangangFlow.domain.community",
        "com.hangangFlow.hangangFlow.domain.likes"

})
public class JpaConfig {
    @Bean
    public DataSource dataSource(DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder()
                .url("jdbc:mariadb://solux-server-database.cui0seogsli3.ap-northeast-2.rds.amazonaws.com:3306/solux")
                .username("solux")
                .password("soluxpw00")
                .driverClassName("org.mariadb.jdbc.Driver") // MariaDB의 경우 org.mariadb.jdbc.Driver를 사용합니다.
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPackagesToScan("com.hangangFlow.hangangFlow.domain.user", "com.hangangFlow.hangangFlow.domain.park", "com.hangangFlow.hangangFlow.domain.bookmark",  "com.hangangFlow.hangangFlow.domain.community","com.hangangFlow.hangangFlow.domain.likes");

        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MariaDBDialect"); // MariaDB Dialect
        entityManagerFactoryBean.setJpaProperties(jpaProperties);

        return entityManagerFactoryBean;
    }




    // 트랜잭션 매니저 설정
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}




