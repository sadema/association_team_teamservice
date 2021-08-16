package nl.kristalsoftware.association.team_app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Slf4j
@SpringBootApplication
public class TeamApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamApplication.class, args);
	}

//	@Profile("devlocal")
//	@Bean
//	CommandLineRunner initializeQueryDatabase(RestTemplate restTemplate) {
//		return args -> {
//			AllPlayers allPlayers = getAllPlayers(restTemplate);
//			deleteAllPlayers(restTemplate, allPlayers);
//		};
//	}
//
//	private AllPlayers getAllPlayers(RestTemplate restTemplate) {
//		String url = "http://localhost:5984/teams/_design/team/_view/all-players";
//		ResponseEntity<AllPlayers> allPlayers = restTemplate.getForEntity(url, AllPlayers.class);
//		log.info(allPlayers.toString());
//		return allPlayers.getBody();
//	}
//
//	private void deleteAllPlayers(RestTemplate restTemplate, AllPlayers allPlayers) {
//		String baseUrl = "http://localhost:5984/teams/";
//		allPlayers.getRows().stream()
//				.map(it -> it.getValue())
//				.forEach(it -> {
//					log.info("id: {}, rev: {}", it.get_id(), it.get_rev());
//					String uri = baseUrl + it.get_id() + "?rev=" + it.get_rev();
//					log.info(uri);
//					restTemplate.delete(uri, new HashMap<>());
//				});
//	}

}
