package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.Q5DTO;

@Repository
public interface Q5DTORepository extends Neo4jRepository<Q5DTO, Long> {


}
