package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Match;

@Repository
public interface MatchRepository extends Neo4jRepository<Match, Long> {

    @Query("match (m:Match {idOriginal: $id}) return m")
    Match findByIdOriginal(Long id);


}
