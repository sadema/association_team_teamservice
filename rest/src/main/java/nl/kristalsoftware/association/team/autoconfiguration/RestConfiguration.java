package nl.kristalsoftware.association.team.autoconfiguration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = "nl.kristalsoftware.association.team.rest")
@Configuration
public class RestConfiguration {
}
