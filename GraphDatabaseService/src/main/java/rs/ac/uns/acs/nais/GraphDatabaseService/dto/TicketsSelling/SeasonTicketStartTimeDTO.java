package rs.ac.uns.acs.nais.GraphDatabaseService.dto.TicketsSelling;

import java.util.Date;

public class SeasonTicketStartTimeDTO {

    private Date seasonTicketStartTime;

    public SeasonTicketStartTimeDTO() {
    }

    public SeasonTicketStartTimeDTO(Date seasonTicketStartTime) {
        this.seasonTicketStartTime = seasonTicketStartTime;
    }

    public Date getSeasonTicketStartTime() {
        return seasonTicketStartTime;
    }

    public void setSeasonTicketStartTime(Date seasonTicketStartTime) {
        this.seasonTicketStartTime = seasonTicketStartTime;
    }

    @Override
    public String toString() {
        return "\nSeasonTicketStartTimeDTO{" +
                "seasonTicketStartTime=" + seasonTicketStartTime +
                "}\n";
    }
}


