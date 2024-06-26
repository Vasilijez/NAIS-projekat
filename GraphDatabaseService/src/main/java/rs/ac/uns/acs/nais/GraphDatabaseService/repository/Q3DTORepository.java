package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.Q3DTO;

import java.util.List;

@Repository
public interface Q3DTORepository extends Neo4jRepository<Q3DTO, Long> {

    @Query("MATCH (f:Fan)-[:LIKES]->(p:Player)-[:IS_IN]->(t:Team)<-[:CHEERS]-(f2:Fan {idOriginal: 556})-[:LIKES]->(p2:Player) " +
            "WHERE p.position = p2.position " +
            "WITH f2, p, t " +
            "MATCH (p3:Player)-[:IS_IN]->(t) " +
            "WHERE p3.position = p.position AND NOT EXISTS { MATCH (f2)-[:LIKES]->(p3) } " +
            "WITH p3.name AS playerName, p3.position AS position, t.name AS teamName " +
            "LIMIT 100 " +
            "CREATE (q:Q3DTO {playerName: playerName, position: position, teamName: teamName}) " +
            "RETURN q")
    List<Q3DTO> query3();
//TODO proveriti ovaj id 556 kakve ce rez prikazati posto cu nove podatke ubacivati

//    @Query("MATCH (f:Fan)-[:LIKES]->(p:Player)-[:IS_IN]->(t:Team)<-[:CHEERS]-(f2:Fan {idOriginal: 556})-[:LIKES]->(p2:Player) " +
//            "WHERE p.position = p2.position " +
//            "WITH f2, p, t " +
//            "MATCH (p3:Player)-[:IS_IN]->(t) " +
//            "WHERE p3.position = p.position AND NOT EXISTS { MATCH (f2)-[:LIKES]->(p3) } " +
//            "RETURN DISTINCT new rs.ac.uns.acs.nais.GraphDatabaseService.dto.Q3DTO(p3.name, p3.position, t.name) " +
//            "LIMIT 100")
//    List<Q3DTO> query3();


}
