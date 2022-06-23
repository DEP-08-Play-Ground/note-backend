package ik.ijse.dep8.note.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application.yaml")
@EnableTransactionManagement
public class JPAConfig {

    private Environment environment;

    public JPAConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource ds, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean lcmfb = new LocalContainerEntityManagerFactoryBean();
        lcmfb.setDataSource(ds);
        lcmfb.setJpaVendorAdapter(jpaVendorAdapter);
        lcmfb.setPackagesToScan("lk.ijse.dep8.note");
        return lcmfb;
    }


    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        final HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setShowSql(environment.getProperty("jpa.show-sql", Boolean.class, false));
        jpaVendorAdapter.setGenerateDdl(environment.getProperty("jpa.generate-ddl", Boolean.class, false));
        jpaVendorAdapter.setDatabasePlatform(environment.getRequiredProperty(("jpa.dialect")));
        return jpaVendorAdapter;
    }

    public DataSource dataSource() {
        final HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(environment.getRequiredProperty("hikari.jdbc-url"));
        hikariDataSource.setUsername(environment.getRequiredProperty("hikari.username"));
        hikariDataSource.setPassword(environment.getRequiredProperty("hikari.password"));
        hikariDataSource.setDriverClassName(environment.getRequiredProperty("hikari.driver-classname"));
        hikariDataSource.setMaximumPoolSize(environment.getRequiredProperty("hikari.max-pool-size", Integer.class));
        return hikariDataSource;
    }

    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}