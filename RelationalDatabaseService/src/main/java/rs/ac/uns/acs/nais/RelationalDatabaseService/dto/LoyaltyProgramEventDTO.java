package rs.ac.uns.acs.nais.RelationalDatabaseService.dto;

import rs.ac.uns.acs.nais.RelationalDatabaseService.model.LoyaltyProgram;

public class LoyaltyProgramEventDTO {
    private LoyaltyProgram loyaltyProgram;
    private String requestId;

    public LoyaltyProgramEventDTO() {
    }

    public LoyaltyProgramEventDTO(LoyaltyProgram loyaltyProgram, String requestId) {
        this.loyaltyProgram = loyaltyProgram;
        this.requestId = requestId;
    }

    public LoyaltyProgram getLoyaltyProgram() {
        return loyaltyProgram;
    }

    public void setLoyaltyProgram(LoyaltyProgram loyaltyProgram) {
        this.loyaltyProgram = loyaltyProgram;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
