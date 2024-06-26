package rs.ac.uns.acs.nais.GraphDatabaseService.dto.TicketsSelling;

import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.Seat;

public class RecommendedSeasonTicketSeatsDTO {

    private Seat seat;

    private Integer seatReservationCountEver;

    private String isReserved;

    public RecommendedSeasonTicketSeatsDTO() {
    }

    public RecommendedSeasonTicketSeatsDTO(Seat seat, Integer seatReservationCountEver, String isReserved) {
        this.seat = seat;
        this.seatReservationCountEver = seatReservationCountEver;
        this.isReserved = isReserved;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Integer getSeatReservationCountEver() {
        return seatReservationCountEver;
    }

    public void setSeatReservationCountEver(Integer seatReservationCountEver) {
        this.seatReservationCountEver = seatReservationCountEver;
    }

    public String getIsReserved() {
        return isReserved;
    }

    public void setIsReserved(String isReserved) {
        this.isReserved = isReserved;
    }

    @Override
    public String toString() {
        return "\nRecommendedSeasonTicketSeatsDTO{" +
                "seat=" + seat +
                ", seatReservationCountEver=" + seatReservationCountEver +
                ", isReserved='" + isReserved + '\'' +
                "}\n";
    }
}
