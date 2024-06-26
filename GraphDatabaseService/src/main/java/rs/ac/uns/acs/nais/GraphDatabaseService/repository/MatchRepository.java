package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Match;

import java.util.List;

@Repository
public interface MatchRepository extends Neo4jRepository<Match, Long> {

    @Query("match (m:Match {idOriginal: $id}) return m")
    Match findByIdOriginal(Long id);

//    @Query("match (m:Match) return m.idOriginal, m.name, m.date")
    @Query("match (m:Match) return m")
    List<Match> findAll();
}
