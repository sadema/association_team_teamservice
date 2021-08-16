package nl.kristalsoftware.association.team.autoconfiguration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Slf4j
@Configuration
//@ComponentScan(basePackages = {
//        "nl.kristalsoftware.association.team.datastore"
//})
@Import(DataStoreConfiguration.class)
public class DataStoreAutoConfiguration {
}
