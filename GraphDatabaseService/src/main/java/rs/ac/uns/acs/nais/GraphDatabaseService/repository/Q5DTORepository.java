package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.neo4j.repository.query.Query;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.Q5DTO;

import java.util.List;

@Repository
public interface Q5DTORepository extends Neo4jRepository<Q5DTO, Long> {

    @Query("MATCH (f:Fan)-[l:LIKES]->(p:Player)-[pl:PLAYED]->(m:Match) " +
            "MATCH (p)-[i:IS_IN]->(t:Team) " +
            "WITH f, t, p, pl " +
            "ORDER BY l.score DESC " +
            "// top 3 igraca koje vole fanovi " +
            "WITH f, t, collect(p)[..3] AS top3Players " +
            "UNWIND top3Players AS topPlayer " +
            "// prosek top 3 igraca " +
            "MATCH (topPlayer)-[pl:PLAYED]->(m:Match) " +
            "WITH f, t, avg(pl.goalsScored) AS avgGoalsTop, avg(pl.minutesPlayed) AS avgMinutesTop, avg(pl.foulsMade) AS avgFoulsTop, avg(pl.assists) AS avgAssistsTop " +
            "// svi ostali igraci iz tog tima " +
            "MATCH (t)<-[:IS_IN]-(otherPlayer:Player) " +
            "WHERE NOT (f)-[:LIKES]->(otherPlayer) " +
            "WITH f, t, avgGoalsTop, avgMinutesTop, avgFoulsTop, avgAssistsTop, otherPlayer " +
            "MATCH (otherPlayer)-[pl:PLAYED]->(m:Match n" +
            "WITH t, avgGoalsTop, avgMinutesTop, avgFoulsTop, avgAssistsTop, avg(pl.goalsScored) AS avgGoalsOther, avg(pl.minutesPlayed) AS avgMinutesOther, avg(pl.foulsMade) AS avgFoulsOther, avg(pl.assists) AS avgAssistsOther " +
            "// proseci grupisani po timovima " +
            "LIMIT 100 " +
            "CREATE (q:Q5DTO {teamName: teamName, avgGoalsTop: avgGoalsTop, avgMinutesTop: avgMinutesTop, avgFoulsTop: avgFoulsTop, avgAssistsTop: avgAssistsTop, avgGoalsOther: avgGoalsOther, avgMinutesOther: avgMinutesOther, avgFoulsOther: avgFoulsOther, avgAssistsOther: avgAssistsOther}) " +
            "RETURN q")
    List<Q5DTO> query5();
}
