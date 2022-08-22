package fr.epita.netflix;

import org.hibernate.dialect.PostgreSQL10Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@SpringBootApplication()
public class MovieBackendApp {
    static {
        System.setProperty("application.file", "src/main/resources/application.properties");
    }

    public static void main(String[] args) {
        SpringApplication.run(MovieBackendApp.class);
    }


    @Bean
    public DataSource dataSource() {
        return new DriverManagerDataSource("jdbc:postgresql://localhost:5432/postgres", "postgres", "user123");
    }

    @Bean(name = "entityManagerFactory")
    public LocalSessionFactoryBean sessionFactoryBean(@Autowired DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("fr.epita.netflix.datamodel");
        Properties properties = new Properties();
        properties.setProperty("hibernate.properties", PostgreSQL10Dialect.class.getName());
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.show_sql", "true");
        sessionFactory.setHibernateProperties(properties);
        return sessionFactory;
    }

}
