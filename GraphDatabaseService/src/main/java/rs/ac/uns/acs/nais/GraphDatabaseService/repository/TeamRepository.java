package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Team;

@Repository
public interface TeamRepository extends Neo4jRepository<Team, Long> {

    @Query("match (t:Team {idOriginal: $id}) return t")
    Team findByIdOriginal(Long id);
}
