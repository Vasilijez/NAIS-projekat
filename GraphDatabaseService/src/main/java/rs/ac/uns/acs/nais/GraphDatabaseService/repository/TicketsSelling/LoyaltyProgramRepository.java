package rs.ac.uns.acs.nais.GraphDatabaseService.repository.TicketsSelling;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.LoyaltyProgram;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.Order;

import java.util.List;

@Repository
public interface LoyaltyProgramRepository extends Neo4jRepository<LoyaltyProgram, Long> {
//    Customer findByEmail(String email);

    @Query("CREATE (lp1:LoyaltyProgram {level: $level, discountRate: $discountRate}) " +
            "RETURN lp1 ")
    LoyaltyProgram createLoyaltyProgram(String level, Double discountRate);

    @Query("OPTIONAL MATCH (lp:LoyaltyProgram) " +
            "WHERE lp.level = $level " +
            "WITH lp " +
            "WHERE lp IS NULL " +
            "CREATE (newLp: LoyaltyProgram {level: $level, discountRate: $discountRate}) " +
            "RETURN count(newLp) >= 1 AS isCreated ")
    Boolean createLoyaltyProgramSAGA(String level, Double discountRate);


    @Query("MATCH (n:LoyaltyProgram {level: $level}) " +
            "RETURN  n")
    LoyaltyProgram findLoyaltyProgram(String level);

    @Query("MATCH (n:LoyaltyProgram {level: $level}) " +
            "SET n.level = $updatingLevel, n.discountRate = $discountRate " +
            "RETURN n")
    LoyaltyProgram updateLoyaltyProgram(String level, String updatingLevel, Double discountRate);

    @Query("MATCH (n:LoyaltyProgram {level: $level}) " +
            "DETACH DELETE n " +
            "RETURN count(n) > 0 ")
    boolean deleteLoyaltyProgram(String level);

    @Query("MATCH (:Fan {username: $username})-[r:MEMBER_OF]->(lp:LoyaltyProgram) " +
            "RETURN lp ")
    List<LoyaltyProgram> findMemberOfRelationship(String username);

}
