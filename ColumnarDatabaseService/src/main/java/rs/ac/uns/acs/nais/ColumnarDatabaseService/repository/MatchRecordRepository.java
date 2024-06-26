package rs.ac.uns.acs.nais.ColumnarDatabaseService.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Income;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.MatchRecord;

import java.util.UUID;

@Repository
public interface MatchRecordRepository extends CassandraRepository<MatchRecord, UUID> {
}
