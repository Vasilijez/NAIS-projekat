package rs.ac.uns.acs.nais.GraphDatabaseService.repository.TicketsSelling;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.LoyaltyProgram;

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


//    @Query("MATCH (c:Customer {idOriginal: $customerId}) " +
//            "OPTIONAL MATCH (c)-[r:PURCHASED]->(p:Product {idOriginal: $productId}) " +
//            "WITH c, p, COALESCE(r, 0) AS rel " +
//            "MERGE (c)-[purchase: PURCHASED]->(p) " +
//            "ON CREATE SET purchase.numberOfPurchasedItems = 1 " +
//            "ON MATCH SET purchase.numberOfPurchasedItems = rel.numberOfPurchasedItems + 1")
//    void purchaseProduct(Long customerId, String productId);
//
//
//    @Query("MATCH (c:Customer {idOriginal: $customerId})-[r:PURCHASED]->(p:Product {idOriginal: $productId}) " +
//            "RETURN count(r) > 0")
//    boolean hasPurchasedProduct(Long customerId, String productId);
//
//    @Query("MATCH (c:Customer {idOriginal: $customerId}) " +
//            "MATCH (p:Product {idOriginal: $productId}) " +
//            "CREATE (c)-[purchase:PURCHASED]->(p) " +
//            "SET purchase.numberOfPurchasedItems = 1")
//    void createPurchase(Long customerId, String productId);

}
