package rs.ac.uns.acs.nais.ColumnarDatabaseService.service;


import com.datastax.oss.driver.api.core.cql.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.ExpenseDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.ExpensesCategorized;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Expense;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.enumeration.ExpenseCategory;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.ExpenseRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@EnableCaching
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expense> getAll() {
        return expenseRepository.findAll();
    }

    public Expense create(ExpenseDTO dto) {
        Expense newExpense = new Expense();
        newExpense.setExpenseId(UUID.randomUUID());
        newExpense.setExpenseCreationTimestamp(dto.getExpenseCreationTimestamp());
        newExpense.setAmount(dto.getAmount());
        newExpense.setDescription(dto.getDescription());
        newExpense.setCategory(ExpenseCategory.valueOf(dto.getCategory().toUpperCase()));

        return expenseRepository.save(newExpense);

    }


    public Expense update(UUID id, Expense expense) {
        Optional<Expense> existingExpense = expenseRepository.findById(id);
        if (existingExpense.isPresent()) {
            Expense updatedExpense = existingExpense.get();
            updatedExpense.setDescription(expense.getDescription());
            updatedExpense.setAmount(expense.getAmount());
            updatedExpense.setCategory(expense.getCategory());
            updatedExpense.setExpenseCreationTimestamp(expense.getExpenseCreationTimestamp());
            return expenseRepository.save(updatedExpense);
        } else {
            throw new RuntimeException("Expense not found with id " + id);
        }
    }

    public void delete(UUID id) {
        expenseRepository.deleteById(id);
    }

    public void createExpenses(List<ExpenseDTO> DTOs) {
        List<Expense> newExpenses = new ArrayList<Expense>();

        for (ExpenseDTO dto: DTOs) {
            Expense newExpense = new Expense();
            newExpense.setExpenseId(UUID.randomUUID());
            newExpense.setCategory(ExpenseCategory.valueOf(dto.getCategory().toUpperCase()));
            newExpense.setExpenseCreationTimestamp(dto.getExpenseCreationTimestamp());
            newExpense.setAmount(dto.getAmount());
            newExpense.setDescription(dto.getDescription());
            System.out.println(newExpense.getExpenseCreationTimestamp());
            System.out.println("dto: " + dto.getExpenseCreationTimestamp());
            newExpenses.add(newExpense);
        }
        expenseRepository.saveAll(newExpenses);
    }

    public List<ExpensesCategorized> perCategory() {
        LocalDateTime startDate = LocalDateTime.of(2022,01, 01,00,00, 00);
        LocalDateTime endDate = LocalDateTime.of(2023,01, 01,00,00, 00);

        List<Object[]> results = expenseRepository.findAverageAmountByCategory(startDate, endDate);
        List<ExpensesCategorized> dtos = new ArrayList<>();

        // Assuming results is Object[] where each element is Object[]
        for (Object obj : results) {
            Object[] result = (Object[]) obj; // Cast each element to Object[]

            // Assuming the first element is the Row object (if you have multiple columns, adjust accordingly)
            Row row = (Row) result[0]; // Cast the first element to Row

            // Example: Assuming category is a string column
            String category = row.getString("category");

            // Example: Assuming average_amount is a double column
            Double averageAmount = row.getDouble("average");

            ExpensesCategorized dto = new ExpensesCategorized();
            dto.setCategory(category);
            dto.setAverage(averageAmount);

            dtos.add(dto);
        }

        return dtos;
    }
}
