package rs.ac.uns.acs.nais.ColumnarDatabaseService.dto;

public class ExpenseDTO {

    private String description;
    private Double amount;
    private String category;

    public ExpenseDTO() {
    }

    public ExpenseDTO(String description, Double amount, String category) {
        this.description = description;
        this.amount = amount;
        this.category = category;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
