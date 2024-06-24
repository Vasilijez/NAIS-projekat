package rs.ac.uns.acs.nais.ColumnarDatabaseService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.IncomeDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Income;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.IncomeRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@EnableCaching
public class IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;



    public Income create(IncomeDTO dto) {
        Income newIncome = new Income(dto);
        newIncome.setIncomeId(UUID.randomUUID());

        return incomeRepository.save(newIncome);
    }

    public Income updateIncome(UUID id, Income income) {
        Optional<Income> existingIncome = incomeRepository.findById(id);
        if (existingIncome.isPresent()) {
            Income updatedIncome = existingIncome.get();
            updatedIncome.setIncomeCreationTimestamp(income.getIncomeCreationTimestamp());
            updatedIncome.setDescription(income.getDescription());
            updatedIncome.setAmount(income.getAmount());
            updatedIncome.setSource(income.getSource());
            return incomeRepository.save(updatedIncome);
        } else {
            throw new RuntimeException("Income not found with id " + id);
        }
    }

    public List<Income> getAll() {
        return incomeRepository.findAll();
    }

    public void createIncomes(List<IncomeDTO> DTOs) {
        List<Income> newIncomes = new ArrayList<Income>();
        for (IncomeDTO dto: DTOs) {
            Income newIncome= new Income(dto);
            newIncome.setIncomeId(UUID.randomUUID());
            newIncomes.add(newIncome);
        }
        incomeRepository.saveAll(newIncomes);
    }

//    public void deleteIncome(String incomeId, LocalDateTime timestamp) {
//        try {
//            incomeRepository.deleteByIdAndTimestamp(incomeId, timestamp);
//        } catch (Exception e) {
//            // Log the exception
//            e.printStackTrace(); // Replace with proper logging framework
//            // Optionally re-throw or handle the exception
//            throw new RuntimeException("Failed to delete income with id " + incomeId, e);
//        }
//    }

}
