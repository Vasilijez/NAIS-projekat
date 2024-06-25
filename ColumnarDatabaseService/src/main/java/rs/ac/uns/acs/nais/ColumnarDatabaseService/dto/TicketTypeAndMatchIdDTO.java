package rs.ac.uns.acs.nais.ColumnarDatabaseService.dto;

import java.util.List;
import java.util.UUID;

public class TicketTypeAndMatchIdDTO {

    public UUID matchId;
    public List<TicketSalesPerTypeDTO> ticketsPerType;

    public TicketTypeAndMatchIdDTO() {
    }

    public TicketTypeAndMatchIdDTO(UUID matchId, List<TicketSalesPerTypeDTO> ticketsPerType) {
        this.matchId = matchId;
        this.ticketsPerType = ticketsPerType;
    }

}
