package Telekocsi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import javax.sql.DataSource;
import org.springframework.http.HttpHeaders;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.sql.SQLException;


@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class Main {
    private static DataSource dataSource;
    public static final HttpHeaders defaultHeaders = createDefaultHeaders();
    public static void main(String[] args) throws SQLException {
        dataSource = getDataSource();
        dataSource.getConnection();
        JdbcTemplate template = new JdbcTemplate(dataSource);
        int x = template.queryForObject("select count(*) from users", Integer.class);
        System.out.println(x);
        SpringApplication.run(Main.class, args);
    }
    private static HttpHeaders createDefaultHeaders() {
        var headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        return headers;
    }

    public static DriverManagerDataSource getDataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        dataSource.setUrl("mysql://localhost:3306/telekocsi");

        dataSource.setUsername("root");

        dataSource.setPassword("root");

        return dataSource;
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }



}
