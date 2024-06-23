package rs.ac.uns.acs.nais.ColumnarDatabaseService.dto;

import jnr.constants.platform.Local;

import java.time.LocalDateTime;

public class IncomeDTO {

    private LocalDateTime incomeCreationTimestamp;
    private String description;
    private Double amount;
    private String source;

    public IncomeDTO() {
    }

    public IncomeDTO(LocalDateTime incomeCreationTimestamp, String description, Double amount, String source) {
        this.incomeCreationTimestamp = incomeCreationTimestamp;
        this.description = description;
        this.amount = amount;
        this.source = source;
    }

    public LocalDateTime getIncomeCreationTimestamp() {
        return incomeCreationTimestamp;
    }

    public void setIncomeCreationTimestamp(LocalDateTime incomeCreationTimestamp) {
        this.incomeCreationTimestamp = incomeCreationTimestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
