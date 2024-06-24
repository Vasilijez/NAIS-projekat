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

    @PrimaryKeyColumn(name = "year", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private int year;

    @PrimaryKeyColumn(name = "month", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private int month;

    @PrimaryKeyColumn(name = "income_id", ordinal = 2, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private UUID incomeId;

    @Column("income_creation_timestamp")
    private LocalDateTime incomeCreationTimestamp;

    private String description;
    private Double amount;
    private String source;

    public Income(IncomeDTO dto) {
        this.incomeCreationTimestamp = dto.getIncomeCreationTimestamp();
        this.description = dto.getDescription();
        this.amount = dto.getAmount();
        this.source = dto.getSource();

        // Extract year and month from incomeCreationTimestamp
        this.year = dto.getIncomeCreationTimestamp().getYear();
        this.month = dto.getIncomeCreationTimestamp().getMonthValue();
    }

    public Income(UUID incomeId, LocalDateTime incomeCreationTimestamp, String description, Double amount, String source) {
        this.incomeId = incomeId;
        this.incomeCreationTimestamp = incomeCreationTimestamp;
        this.description = description;
        this.amount = amount;
        this.source = source;

        // Extract year and month from incomeCreationTimestamp
        this.year = incomeCreationTimestamp.getYear();
        this.month = incomeCreationTimestamp.getMonthValue();
    }

    public Income() {
    }

    // Getters and setters
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
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

        // Update year and month when setting incomeCreationTimestamp
        this.year = incomeCreationTimestamp.getYear();
        this.month = incomeCreationTimestamp.getMonthValue();
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
