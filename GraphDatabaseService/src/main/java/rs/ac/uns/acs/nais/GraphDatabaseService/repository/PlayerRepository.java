package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.Q2DTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Player;

@Repository
public interface PlayerRepository extends Neo4jRepository<Player, Long> {

    @Query("match (p:Player {idOriginal: $id}) return p")
    Player findByIdOriginal(Long id);

}
