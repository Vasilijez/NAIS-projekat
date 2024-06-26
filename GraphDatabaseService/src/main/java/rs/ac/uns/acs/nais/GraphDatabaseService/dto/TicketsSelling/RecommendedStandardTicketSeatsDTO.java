package rs.ac.uns.acs.nais.GraphDatabaseService.dto.TicketsSelling;

import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.Seat;

public class RecommendedStandardTicketSeatsDTO {

    private Seat seat;

    private Integer seatReservationCount;

    private String isReserved;

    public RecommendedStandardTicketSeatsDTO(Seat seat, Integer seatReservationCount, String isReserved) {
        this.seat = seat;
        this.seatReservationCount = seatReservationCount;
        this.isReserved = isReserved;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Integer getSeatReservationCount() {
        return seatReservationCount;
    }

    public void setSeatReservationCount(Integer seatReservationCount) {
        this.seatReservationCount = seatReservationCount;
    }

    public String getIsReserved() {
        return isReserved;
    }

    public void setIsReserved(String isReserved) {
        this.isReserved = isReserved;
    }

    @Override
    public String toString() {
        return "\nRecommendedStandardTicketSeatsDTO {" +
                "seat=" + seat +
                ", seatReservationCount=" + seatReservationCount +
                ", isReserved='" + isReserved + '\'' +
                "}\n";
    }
}
