package rs.ac.uns.acs.nais.GraphDatabaseService.dto.TicketsSelling;

import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.Fan;

public class TwoBottomFansDTO {

    private Fan fan;

    private Double pointsNumber;

    public TwoBottomFansDTO(Fan fan, Double pointsNumber) {
        this.fan = fan;
        this.pointsNumber = pointsNumber;
    }

    public Fan getFan() {
        return fan;
    }

    public void setFan(Fan fan) {
        this.fan = fan;
    }

    public Double getPointsNumber() {
        return pointsNumber;
    }

    public void setPointsNumber(Double pointsNumber) {
        this.pointsNumber = pointsNumber;
    }

    @Override
    public String toString() {
        return "\nTwoBottomFansDTO {" +
                "fan=" + fan +
                ", pointsNumber=" + pointsNumber +
                '}' + '\n';
    }
}
