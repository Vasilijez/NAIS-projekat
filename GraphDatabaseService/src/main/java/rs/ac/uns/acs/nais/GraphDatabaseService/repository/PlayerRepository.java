package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Player;

import java.util.List;

@Repository
public interface PlayerRepository extends Neo4jRepository<Player, Long> {

    @Query("match (p:Player {idOriginal: $id}) return p")
    Player findByIdOriginal(Long id);

//    @Query("match (p:Player) return p.idOriginal, p.name, p.lastName, p.position, p.age")
    @Query("match (p:Player) return p")
    List<Player> findAll();
}
