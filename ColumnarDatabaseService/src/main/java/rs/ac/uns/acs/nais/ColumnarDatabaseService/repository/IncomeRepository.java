package rs.ac.uns.acs.nais.ColumnarDatabaseService.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Income;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface IncomeRepository extends CassandraRepository<Income, UUID> {
//
//    @Query("DELETE FROM nais.income WHERE id = :incomeId AND incomeCreationTimestamp = :timestamp")
//    void deleteByIdAndTimestamp(@Param("incomeId") String incomeId, @Param("timestamp") LocalDateTime timestamp);
}

