package rs.ac.uns.acs.nais.ColumnarDatabaseService.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.ExpenseDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.IncomeDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Income;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.service.IncomeService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/income")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @GetMapping("/all")
    public List<Income> getAll(){
        return incomeService.getAll();
    }
    @PostMapping
    public Income create(@RequestBody IncomeDTO dto) {
        return incomeService.create(dto);
    }

    @PutMapping("/{id}")
    public Income updateIncome(@PathVariable UUID id, @RequestBody Income income) {
        return incomeService.updateIncome(id, income);
    }

//    @DeleteMapping("/{id}/{timestamp}")
//    public void deleteIncome(@PathVariable String id, LocalDateTime timestamp) {
//        incomeService.deleteIncome(id, timestamp);
//    }

    @PostMapping("/add-list")
    public void createIncomes(@RequestBody List<IncomeDTO> DTOs){
        incomeService.createIncomes(DTOs);
    }
}
