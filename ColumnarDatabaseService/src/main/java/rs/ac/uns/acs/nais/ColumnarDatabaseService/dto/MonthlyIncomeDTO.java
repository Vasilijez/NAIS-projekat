package rs.ac.uns.acs.nais.ColumnarDatabaseService.dto;

public class MonthlyIncomeDTO {
    private String month;
    private Double totalIncome;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Double totalIncome) {
        this.totalIncome = totalIncome;
    }
}
