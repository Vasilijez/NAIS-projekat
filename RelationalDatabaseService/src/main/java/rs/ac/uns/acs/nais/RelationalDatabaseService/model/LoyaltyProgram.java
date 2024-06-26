package rs.ac.uns.acs.nais.RelationalDatabaseService.model;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import rs.ac.uns.acs.nais.RelationalDatabaseService.model.enumeration.Gender;

@Entity
@Table(name = "loyalty_program")
public class LoyaltyProgram {
	
//	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

	@Id
	private String level;

    @Column(name = "discount_rate")
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
