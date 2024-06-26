package rs.ac.uns.acs.nais.ColumnarDatabaseService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.ExpenseDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.ExpensesCategorized;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Expense;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.service.ExpenseService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;


    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAll();
    }

    @PostMapping
    public Expense createExpense(@RequestBody ExpenseDTO dto) {
        return expenseService.create(dto);
    }
    @PutMapping("/{id}")
    public Expense updateExpense(@PathVariable UUID id, @RequestBody Expense expense) {
        return expenseService.update(id, expense);
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable UUID id) {
        expenseService.delete(id);
    }

    @PostMapping("/add-list")
    public void createExpenses(@RequestBody List<ExpenseDTO> DTOs){
         expenseService.createExpenses(DTOs);
    }

    @GetMapping("/per-category")
    public List<ExpensesCategorized> perCategoryInYear2022(){
        return expenseService.perCategory();
    }

    @GetMapping("/all/{category}")
    public List<ExpenseDTO> getAllByCategory(@PathVariable String category){
        return expenseService.getAllByCategory(category);
    }
}