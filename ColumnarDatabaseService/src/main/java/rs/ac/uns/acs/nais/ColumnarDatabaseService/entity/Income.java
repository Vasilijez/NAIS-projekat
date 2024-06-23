package rs.ac.uns.acs.nais.ColumnarDatabaseService.entity;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.IncomeDTO;

import java.time.LocalDateTime;
import java.util.UUID;

@Table("incomes")
public class Income {

    @PrimaryKeyColumn(name = "income_id", type = PrimaryKeyType.PARTITIONED)
    private UUID incomeId;

    @PrimaryKeyColumn(name = "date", ordinal = 0, ordering = Ordering.DESCENDING)
    private LocalDateTime incomeCreationTimestamp;

    private String description;
    private Double amount;
    private String source;

    public Income(IncomeDTO dto) {
        this.incomeCreationTimestamp = dto.getIncomeCreationTimestamp();
        this.description = dto.getDescription();
        this.amount = dto.getAmount();
        this.source = dto.getSource();
    }

    public Income(UUID incomeId, LocalDateTime incomeCreationTimestamp, String description, Double amount, String source) {
        this.incomeId = incomeId;
        this.incomeCreationTimestamp = incomeCreationTimestamp;
        this.description = description;
        this.amount = amount;
        this.source = source;
    }

    public Income() {
    }

    public UUID getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(UUID incomeId) {
        this.incomeId = incomeId;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
