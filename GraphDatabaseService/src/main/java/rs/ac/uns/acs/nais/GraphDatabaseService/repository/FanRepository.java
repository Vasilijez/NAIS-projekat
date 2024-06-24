package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Fan;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Player;

@Repository
public interface FanRepository extends Neo4jRepository<Fan, Long> {

    @Query("match (f:Fan {idOriginal: $id}) return f")
    Fan findByIdOriginal(Long id);
}
