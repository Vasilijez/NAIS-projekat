package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.Q1DTO;
import org.springframework.data.neo4j.repository.query.Query;

@Repository
public interface Q1DTORepository extends Neo4jRepository<Q1DTO, Long> {

    @Query("MATCH (f:Fan)-[:WATCHES]->(m:Match)<-[:PARTICIPATED]-(t:Team)<-[:CHEERS]-(f) " +
            "WITH t, m, " +
            "CASE WHEN (t)-[:PARTICIPATED {isWinner: true}]->(m) THEN 1 ELSE 0 END AS pobednik, " +
            "CASE WHEN (t)-[:PARTICIPATED {isWinner: false}]->(m) THEN 1 ELSE 0 END AS gubitnik " +
            "WITH sum(pobednik) AS numWins, sum(gubitnik) AS numLosses " +
            "CREATE (q:Q1DTO {numWins: numWins, numLosses: numLosses}) " +
            "RETURN q")
    Q1DTO query1();
}
