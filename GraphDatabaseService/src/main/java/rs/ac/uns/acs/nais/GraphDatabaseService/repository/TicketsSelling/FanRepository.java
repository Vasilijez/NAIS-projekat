package rs.ac.uns.acs.nais.GraphDatabaseService.repository.TicketsSelling;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.TicketsSelling.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.Fan;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.Order;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface FanRepository extends Neo4jRepository<Fan, Long> {

    @Query("CREATE (f:Fan {username: $username, fanType: $fanType, favouritePlayer: $favouritePlayer, dateOfBirth: $dateOfBirth, gender: $gender, level: $level }) " +
            "RETURN f ")
    Fan createFan(String username, String fanType, String favouritePlayer, LocalDate dateOfBirth, String gender, String level);

    @Query("MATCH (f:Fan {username: $username})  " +
            "RETURN f ")
    Fan findFan(String username);

    @Query("MATCH (f:Fan {username: $username})  " +
            "SET f.username = $updatingUsername, f.fanType = $fanType, f.favouritePlayer = $favouritePlayer, f.dateOfBirth = $dateOfBirth, f.gender = $gender, f.level = $level " +
            "RETURN f ")
    Fan updateFan(String username, String updatingUsername, String fanType, String favouritePlayer, LocalDate dateOfBirth, String gender, String level);

    @Query("MATCH (f:Fan {username: $username}) " +
            "DETACH DELETE f " +
            "RETURN count(f) > 0 ")
    boolean deleteFan(String username);

    @Query("MATCH (f:Fan {username: $username}), (o:Order {orderID: $orderID}) " +
            "CREATE (f)-[r:CREATES_ORDER]->(o) " +
            "RETURN count(r) > 0 ")
    boolean createCreatesOrderRelationship(String username, String orderID);

    @Query("MATCH (:Fan {username: $username})-[r:CREATES_ORDER]->(o:Order) " +
            "RETURN o ")
    List<Order> findCreatesOrderRelationships(String username);

    @Query("MATCH (f:Fan)-[r:MEMBER_OF]->(:LoyaltyProgram), (f)-[:CREATES_ORDER]->(o:Order) " +
            "WHERE o.creationDate >= date(\"2024-01-01\") " +
            "RETURN DISTINCT f as fan, r.pointsNumber as pointsNumber " +
            "ORDER BY r.pointsNumber ASC ")
    List<TwoBottomFansDTO> findTwoFansWithLowestPointsAndAtLeastOneOrderThisYear();

    @Query(" MATCH (f:Fan)  "+
            "WHERE f.level = \"Level_2\" OR f.level = \"Level_1\" " +
            "WITH f " +
            "MATCH (f)-[:CREATES_ORDER]->(o:Order) " +
            "WHERE o.status <> \"Cancelled\" AND o.status <> \"InCart\" " +
            "WITH f, COUNT(o) AS ordersNumber " +
            "WHERE ordersNumber >= 3 " +
            "MATCH (f)-[r:MEMBER_OF]->(:LoyaltyProgram) " +
            "WITH r.pointsNumber AS oldPointsNumber, f, r, ordersNumber " +
            "SET r.pointsNumber = r.pointsNumber + 10 " +
            "RETURN f as fan, ordersNumber, oldPointsNumber, r.pointsNumber AS newPointsNumber ")
    List <FanLoyaltyProgramPointsUpdatingDTO> updateFanLoyaltyProgramPoints();


}
