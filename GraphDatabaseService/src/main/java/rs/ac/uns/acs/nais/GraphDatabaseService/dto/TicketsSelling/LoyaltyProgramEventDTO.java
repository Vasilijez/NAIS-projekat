package rs.ac.uns.acs.nais.GraphDatabaseService.dto.TicketsSelling;

import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.LoyaltyProgram;

public class LoyaltyProgramEventDTO {
    private RelationalDBLoyaltyProgramDTO loyaltyProgram;
    private String requestId;

    public LoyaltyProgramEventDTO() {
    }

    public LoyaltyProgramEventDTO(RelationalDBLoyaltyProgramDTO loyaltyProgram, String requestId) {
        this.loyaltyProgram = loyaltyProgram;
        this.requestId = requestId;
    }

    public LoyaltyProgramEventDTO(LoyaltyProgram loyaltyProgram, String requestId) {
        this.loyaltyProgram = new RelationalDBLoyaltyProgramDTO();
        this.loyaltyProgram.setLevel(loyaltyProgram.getLevel());
        this.loyaltyProgram.setDiscount_rate(loyaltyProgram.getDiscountRate());
        this.requestId = requestId;
    }

    public RelationalDBLoyaltyProgramDTO getLoyaltyProgram() {
        return loyaltyProgram;
    }

    public void setLoyaltyProgram(RelationalDBLoyaltyProgramDTO loyaltyProgram) {
        this.loyaltyProgram = loyaltyProgram;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
