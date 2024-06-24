package rs.ac.uns.acs.nais.ColumnarDatabaseService.entity;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.enumeration.TicketType;

import java.time.LocalDateTime;
import java.util.UUID;

@Table("ticket_sales")
public class TicketSale {
    @PrimaryKeyColumn(name = "match_id", type = PrimaryKeyType.PARTITIONED)
    private UUID matchId;

    @PrimaryKeyColumn(name = "ticket_type", ordinal = 0, ordering = Ordering.DESCENDING)
    private TicketType ticketType;

    @PrimaryKeyColumn(name = "sale_date", ordinal = 1, ordering = Ordering.DESCENDING)
    private LocalDateTime saleDate;
    @PrimaryKeyColumn(name = "ticket_id", ordinal = 2, ordering = Ordering.DESCENDING)
    private UUID ticketId;
    private double amount;
    private int quantity;
    public TicketSale() {
    }

    public UUID getTicketId() {
        return ticketId;
    }

    public void setTicketId(UUID ticketId) {
        this.ticketId = ticketId;
    }

    public UUID getMatchId() {
        return matchId;
    }

    public void setMatchId(UUID matchId) {
        this.matchId = matchId;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
