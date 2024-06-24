package rs.ac.uns.acs.nais.ColumnarDatabaseService.dto;

public class TicketSalesPerTypeDTO {
    public String ticketType;
    public Integer count;

    public TicketSalesPerTypeDTO() {
    }


    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
