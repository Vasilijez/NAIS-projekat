package rs.ac.uns.acs.nais.ColumnarDatabaseService.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.ExpenseDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.ExpensesDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Expense;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Income;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.enumeration.ExpenseCategory;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.ExpenseRepository;

import java.time.Clock;
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
}
