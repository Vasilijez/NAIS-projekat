package rs.ac.uns.acs.nais.ColumnarDatabaseService.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.ExpenseDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.ExpensesCategorized;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Expense;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ExpenseRepository extends CassandraRepository<Expense, UUID> {


//    @Query("SELECT category, AVG(amount) AS average\n" +
//            "FROM nais.expenses\n" +
//            "GROUP BY category;\n")
//    List<ExpensesCategorized> perCategory();
@Query("SELECT category, AVG(amount) AS average " +
        "FROM expenses " +
        "WHERE expense_creation_timestamp >= :startDate " +
        "AND expense_creation_timestamp <= :endDate " +
        "GROUP BY category "+
        "ALLOW FILTERING")
    List<Object[]> findAverageAmountByCategory(LocalDateTime startDate, LocalDateTime endDate);


@Query("SELECT * " +
        "FROM expenses " +
        "WHERE category = :category")
    List<Expense> getAllByCategory(String category);
}
