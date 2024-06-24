package rs.ac.uns.acs.nais.ColumnarDatabaseService.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.TicketSale;

import java.util.List;
import java.util.UUID;

@Repository
public interface TicketSaleRepository extends CassandraRepository<TicketSale, UUID> {

    @Query("SELECT ticket_type, SUM(quantity) AS count " +
            "FROM ticket_sales " +
            "WHERE match_id = ?0 " +
            "GROUP BY ticket_type ")
    List<Object[]> getTicketSalesPerType(UUID matchId);
}
