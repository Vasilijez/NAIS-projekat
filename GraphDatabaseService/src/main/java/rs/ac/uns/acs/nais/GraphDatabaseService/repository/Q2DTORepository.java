package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.Q2DTO;

@Repository
public interface Q2DTORepository extends Neo4jRepository<Q2DTO, Long> {

    @Query("MATCH (f:Fan)-[:LIKES]->(p:Player)-[pl:PLAYED]->(m:Match) " +
            "WITH avg(pl.goalsScored) AS avgGoalsLiked " +
            "MATCH (p:Player)-[pl:PLAYED]->(m:Match) " +
            "OPTIONAL MATCH (f:Fan)-[:LIKES]->(p) " +
            "WHERE f IS NULL " +
            "WITH avgGoalsLiked, avg(pl.goalsScored) AS avgGoalsUnliked " +
            "CREATE (q:Q2DTO {avgGoalsLiked: avgGoalsLiked, avgGoalsUnliked: avgGoalsUnliked}) " +
            "RETURN q")
    Q2DTO query2();
}
