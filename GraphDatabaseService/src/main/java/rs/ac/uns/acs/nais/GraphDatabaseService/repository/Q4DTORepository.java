package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.Q4DTO;

import java.util.List;

@Repository
public interface Q4DTORepository extends Neo4jRepository<Q4DTO, Long> {

    @Query("MATCH (t:Team)-[:PARTICIPATED]->(m:Match)<-[:PLAYED]-(p:Player)<-[:LIKES]-(f:Fan) " +
            "WHERE m.date > '2024-02-01' AND m.date < '2024-03-01' AND t.isWinner = true AND f.score > 7 " +
            "WITH p.name as playerName, p.age as playerAge, count(DISTINCT f) AS numFans " +
            "LIMIT 100 " +
            "CREATE (q:Q4DTO {playerName: playerName, playerAge: playerAge, numFans: numFans}) " +
            "RETURN q")
    List<Q4DTO> query4();
}
