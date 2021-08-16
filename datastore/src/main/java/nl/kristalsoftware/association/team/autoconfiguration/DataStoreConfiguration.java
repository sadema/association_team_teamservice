package nl.kristalsoftware.association.team.autoconfiguration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@ComponentScan(basePackages = "nl.kristalsoftware.association.team.datastore")
@EnableJpaRepositories(basePackages = "nl.kristalsoftware.association.team.datastore")
@EntityScan(basePackages = "nl.kristalsoftware.association.team.datastore")
@Configuration
public class DataStoreConfiguration {

    @Bean
    RestTemplate createRestTemplate() {
        return new RestTemplate();
    }

}
