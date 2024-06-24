package rs.ac.uns.acs.nais.ColumnarDatabaseService.dto;

import java.util.List;

public class ExpensesDTO {

    public List<ExpenseDTO> expenses;

    public ExpensesDTO() {
    }

    public ExpensesDTO(List<ExpenseDTO> expenses) {
        this.expenses = expenses;
    }

    public List<ExpenseDTO> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<ExpenseDTO> expenses) {
        this.expenses = expenses;
    }
}
