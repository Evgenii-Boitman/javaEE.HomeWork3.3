package by.boitman.config;

import by.boitman.database.config.DatabaseConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(DatabaseConfig.class)
@ComponentScan("by.boitman.service")
@Configuration
public class ServiceConfig {
}
