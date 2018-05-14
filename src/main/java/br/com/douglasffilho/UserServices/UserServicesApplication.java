package br.com.douglasffilho.UserServices;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
public class UserServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServicesApplication.class, args);
    }

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    @Primary
    public DataSource dataSource() {
        String database = System.getenv("HMTF_DB_NAME");
        String username = System.getenv("HMTF_DB_USER");
        String password = System.getenv("HMTF_DB_PWD");

        return DataSourceBuilder
                .create()
                .username(username)
                .password(password)
                .url("jdbc:mysql://localhost:3306/" + database + "?useSSL=false")
                .driverClassName("com.mysql.jdbc.Driver")
                .build();
    }

}
