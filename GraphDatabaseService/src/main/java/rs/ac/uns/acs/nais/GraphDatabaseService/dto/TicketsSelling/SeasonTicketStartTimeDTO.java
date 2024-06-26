package rs.ac.uns.acs.nais.GraphDatabaseService.dto.TicketsSelling;

import java.time.LocalDateTime;
import java.util.Date;

public class SeasonTicketStartTimeDTO {

    private LocalDateTime seasonTicketStartTime;

    public SeasonTicketStartTimeDTO() {
    }

    public SeasonTicketStartTimeDTO(LocalDateTime seasonTicketStartTime) {
        this.seasonTicketStartTime = seasonTicketStartTime;
    }

    public LocalDateTime getSeasonTicketStartTime() {
        return seasonTicketStartTime;
    }

    public void setSeasonTicketStartTime(LocalDateTime seasonTicketStartTime) {
        this.seasonTicketStartTime = seasonTicketStartTime;
    }

    @Override
    public String toString() {
        return "\nSeasonTicketStartTimeDTO{" +
                "seasonTicketStartTime=" + seasonTicketStartTime +
                "}\n";
    }
}


