package rs.ac.uns.acs.nais.ColumnarDatabaseService.dto;

import rs.ac.uns.acs.nais.ColumnarDatabaseService.enumeration.ExpenseCategory;

import java.time.LocalDateTime;

public class ExpenseDTO {
    public String category;
    public LocalDateTime expenseCreationTimestamp;
    public String description;
    public Double amount;


    public ExpenseDTO() {
    }

    public ExpenseDTO(LocalDateTime expenseCreationTimestamp, String description, Double amount, String category) {
        this.expenseCreationTimestamp = expenseCreationTimestamp;
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    public LocalDateTime getExpenseCreationTimestamp() {
        return expenseCreationTimestamp;
    }

    public void setExpenseCreationTimestamp(LocalDateTime expenseCreationTimestamp) {
        this.expenseCreationTimestamp = expenseCreationTimestamp;
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
