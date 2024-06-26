package rs.ac.uns.acs.nais.GraphDatabaseService.dto.TicketsSelling;


public class RelationalDBLoyaltyProgramDTO {
	
	private String level;

    private Double discount_rate;

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Double getDiscount_rate() {
		return discount_rate;
	}

	public void setDiscount_rate(Double discount_rate) {
		this.discount_rate = discount_rate;
	}
}
