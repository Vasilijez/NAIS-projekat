package rs.ac.uns.acs.nais.ColumnarDatabaseService.dto;

public class ExpensesCategorized {

    public String category;
    public Double average;

    public ExpensesCategorized() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }
}
