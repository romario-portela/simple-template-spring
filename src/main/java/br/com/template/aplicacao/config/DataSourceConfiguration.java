package br.com.template.aplicacao.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DataSourceConfiguration {

    @Value("${datasource.username}")
    private String userName;

    @Value("${datasource.password}")
    private String password;

    @Value("${datasource.url}")
    private String jdbcUrl;

    @Value("${datasource.driver}")
    private String driver;

    @Value("${datasource.maxPoolSize:50}")
    private int maxPoolSize;

    @Value("${datasource.miniIdle:3}")
    private int miniIdle;

    @Value("${datasource.connectionTimeoutMs:0}")
    private long connectionTimeoutMs;

    @Bean
    public HikariDataSource datasource() {
        final HikariConfig config = new HikariConfig();
        config.setUsername(userName);
        config.setPassword(password);
        config.setJdbcUrl(jdbcUrl);
        config.setDriverClassName(driver);
        config.setMaximumPoolSize(maxPoolSize);
        config.setMinimumIdle(miniIdle);
        config.setConnectionTimeout(connectionTimeoutMs);
        return new HikariDataSource(config);
    }
}