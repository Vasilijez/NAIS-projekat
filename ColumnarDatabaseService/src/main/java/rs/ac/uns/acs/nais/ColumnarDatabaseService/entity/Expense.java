package rs.ac.uns.acs.nais.ColumnarDatabaseService.entity;


import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.enumeration.ExpenseCategory;

import java.time.LocalDateTime;
import java.util.UUID;

@Table("expenses")
public class Expense {

    @PrimaryKeyColumn(name = "category", type = PrimaryKeyType.PARTITIONED)
    private ExpenseCategory category;

    @PrimaryKeyColumn(name = "expense_creation_timestamp", ordinal = 0, ordering = Ordering.DESCENDING)
    private LocalDateTime expenseCreationTimestamp;

    @PrimaryKeyColumn(name = "expense_id", ordinal = 1)
    private UUID expenseId;

    private String description;
    private Double amount;

    public Expense() {
    }

    public Expense(ExpenseCategory category, LocalDateTime expenseCreationTimestamp, UUID expenseId, String description, Double amount) {
        this.category = category;
        this.expenseCreationTimestamp = expenseCreationTimestamp;
        this.expenseId = expenseId;
        this.description = description;
        this.amount = amount;
    }

    public UUID getId() {
        return expenseId;
    }

    public void setExpenseId(UUID incomeId) {
        this.expenseId = incomeId;
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

    public ExpenseCategory getCategory() {
        return category;
    }

    public void setCategory(ExpenseCategory category) {
        this.category = category;
    }
}
