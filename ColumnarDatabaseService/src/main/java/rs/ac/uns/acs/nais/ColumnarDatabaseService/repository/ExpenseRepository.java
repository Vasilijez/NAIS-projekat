package rs.ac.uns.acs.nais.ColumnarDatabaseService.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Expense;

import java.util.UUID;

@Repository
public interface ExpenseRepository extends CassandraRepository<Expense, UUID> {
}
