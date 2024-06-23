package rs.ac.uns.acs.nais.ColumnarDatabaseService.dto;

import rs.ac.uns.acs.nais.ColumnarDatabaseService.enumeration.TicketType;

import java.time.LocalDateTime;
import java.util.UUID;

public class TicketSaleDTO {
    private UUID matchId;
    private TicketType ticketType;
    private LocalDateTime saleDate;
    private double amount;
    private int quantity;

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
