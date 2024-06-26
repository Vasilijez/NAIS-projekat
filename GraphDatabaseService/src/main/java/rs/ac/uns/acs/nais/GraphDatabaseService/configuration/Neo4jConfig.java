package rs.ac.uns.acs.nais.GraphDatabaseService.configuration;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;


@Configuration
public class Neo4jConfig {

    @Value("bolt://neo4j:7687")
    private String uri;

    @Value("neo4j")
    private String username;

    @Value("password")
    private String password;

    @Bean
    public Driver neo4jDriver() {
        return GraphDatabase.driver(uri, AuthTokens.basic(username, password));
    }
}
