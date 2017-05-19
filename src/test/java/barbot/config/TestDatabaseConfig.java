package barbot.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

/**
 * Created by Naveen on 5/19/17.
 */
@EnableAutoConfiguration(exclude={DatabaseConfig.class})
public class TestDatabaseConfig {
}
