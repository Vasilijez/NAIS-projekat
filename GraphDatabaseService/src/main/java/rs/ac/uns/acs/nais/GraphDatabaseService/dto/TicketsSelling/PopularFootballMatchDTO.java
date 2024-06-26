package rs.ac.uns.acs.nais.GraphDatabaseService.dto.TicketsSelling;

import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.FootballMatch;

public class PopularFootballMatchDTO {

    private FootballMatch footballMatch;

    private Integer totalSeatReservationsNumber;

    private String hasTicket;

    public PopularFootballMatchDTO() {
    }

    public PopularFootballMatchDTO(FootballMatch footballMatch, Integer totalSeatReservationsNumber, String hasTicket) {
        this.footballMatch = footballMatch;
        this.totalSeatReservationsNumber = totalSeatReservationsNumber;
        this.hasTicket = hasTicket;
    }

    public FootballMatch getFootballMatch() {
        return footballMatch;
    }

    public void setFootballMatch(FootballMatch footballMatch) {
        this.footballMatch = footballMatch;
    }

    public Integer getTotalSeatReservationsNumber() {
        return totalSeatReservationsNumber;
    }

    public void setTotalSeatReservationsNumber(Integer totalSeatReservationsNumber) {
        this.totalSeatReservationsNumber = totalSeatReservationsNumber;
    }

    public String getHasTicket() {
        return hasTicket;
    }

    public void setHasTicket(String hasTicket) {
        this.hasTicket = hasTicket;
    }

    @Override
    public String toString() {
        return "\nPopularFootballMatchDTO{" +
                "footballMatch=" + footballMatch +
                ", totalSeatReservationsNumber=" + totalSeatReservationsNumber +
                ", hasTicket='" + hasTicket + '\'' +
                "}\n";
    }
}
