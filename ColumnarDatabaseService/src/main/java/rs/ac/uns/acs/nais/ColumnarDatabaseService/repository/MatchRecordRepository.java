package rs.ac.uns.acs.nais.ColumnarDatabaseService.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Income;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.MatchRecord;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.MatchRecordPrimaryKey;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface MatchRecordRepository extends CassandraRepository<MatchRecord, UUID> {

    @Query("SELECT * FROM match_records WHERE match_id = :id AND season = :season")
    MatchRecord findByIdAndSeason(UUID id, Integer season);
}
