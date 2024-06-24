package rs.ac.uns.acs.nais.ColumnarDatabaseService.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.MonthlyIncomeDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Income;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface IncomeRepository extends CassandraRepository<Income, UUID> {
    @Query("SELECT month, sum(amount) as total_income " +
    "FROM incomes "+
    "WHERE year = ?0 " +
    "GROUP BY month "+
    "ALLOW FILTERING")
    List<Object[]> getMonthlyIncomeByYear(Integer year);

    @Query("SELECT * "+
    "FROM incomes "+
    "WHERE year = :year AND month = :month")
    List<Income> getIncomesForYearAndMonth(@Param("year") Integer year,@Param("month") Integer month);
}

