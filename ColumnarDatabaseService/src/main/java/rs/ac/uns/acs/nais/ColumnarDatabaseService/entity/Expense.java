package rs.ac.uns.acs.nais.ColumnarDatabaseService.entity;


import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table("expenses")
public class Expense {

    @PrimaryKeyColumn(name = "expense_id", type = PrimaryKeyType.PARTITIONED)
    private UUID expenseId;

    @PrimaryKeyColumn(name = "date", ordinal = 0, ordering = Ordering.DESCENDING)
    private LocalDateTime expenseCreationTimestamp;

    private String description;
    private Double amount;
    private String category;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
