package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.Q4DTO;

@Repository
public interface Q4DTORepository extends Neo4jRepository<Q4DTO, Long> {


}
