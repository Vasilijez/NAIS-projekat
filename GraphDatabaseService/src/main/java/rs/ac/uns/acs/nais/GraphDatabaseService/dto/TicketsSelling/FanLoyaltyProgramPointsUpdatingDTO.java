package rs.ac.uns.acs.nais.GraphDatabaseService.dto.TicketsSelling;

import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.Fan;

public class FanLoyaltyProgramPointsUpdatingDTO {

    private Fan fan;

    private Integer ordersNumber;

    private Integer oldPointsNumber;

    private Integer newPointsNumber;

    public FanLoyaltyProgramPointsUpdatingDTO(Fan fan, Integer ordersNumber, Integer oldPointsNumber, Integer newPointsNumber) {
        this.fan = fan;
        this.ordersNumber = ordersNumber;
        this.oldPointsNumber = oldPointsNumber;
        this.newPointsNumber = newPointsNumber;
    }

    public Fan getFan() {
        return fan;
    }

    public void setFan(Fan fan) {
        this.fan = fan;
    }

    public Integer getOrdersNumber() {
        return ordersNumber;
    }

    public void setOrdersNumber(Integer ordersNumber) {
        this.ordersNumber = ordersNumber;
    }

    public Integer getOldPointsNumber() {
        return oldPointsNumber;
    }

    public void setOldPointsNumber(Integer oldPointsNumber) {
        this.oldPointsNumber = oldPointsNumber;
    }

    public Integer getNewPointsNumber() {
        return newPointsNumber;
    }

    public void setNewPointsNumber(Integer newPointsNumber) {
        this.newPointsNumber = newPointsNumber;
    }

    @Override
    public String toString() {
        return "\nFanLoyaltyProgramPointsUpdatingDTO{" +
                "fan=" + fan +
                ", ordersNumber=" + ordersNumber +
                ", oldPointsNumber=" + oldPointsNumber +
                ", newPointsNumber=" + newPointsNumber +
                "}\n";
    }
}
