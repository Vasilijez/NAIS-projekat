package rs.ac.uns.acs.nais.ColumnarDatabaseService.service;

import com.datastax.oss.driver.api.core.cql.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.IncomeDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.MonthlyIncomeDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Income;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.IncomeRepository;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static rs.ac.uns.acs.nais.ColumnarDatabaseService.mapper.IncomeMapper.mapper;

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

    public List<MonthlyIncomeDTO> getMonthlyIncomeBy(Integer year) {
        List<Object[]> results=  incomeRepository.getMonthlyIncomeByYear(year);

        List<MonthlyIncomeDTO> dtos = new ArrayList<>();

        for (Object obj:results) {
            Object[] result = (Object[]) obj;
            Row row =(Row) result[0];

            MonthlyIncomeDTO dto = new MonthlyIncomeDTO();
            Integer monthNumber = row.getInt("month");
            dto.setMonth(String.valueOf(Month.of(monthNumber)));
            dto.setTotalIncome(row.getDouble("total_income"));

            dtos.add(dto);

        }
        return dtos;
    }

    public List<IncomeDTO> getIncomesForYearAndMonth(Integer year, Integer month) {

//       return incomeRepository.getIncomesForYearAndMonth(year, month); OVAKO RADI, PROVERI DA LI RADI SA OVIM MAPPEROM
        return incomeRepository.getIncomesForYearAndMonth(year, month)
                .stream()
                .map(mapper::incomeToincomeDTO)
                .collect(Collectors.toList());
    }
}
