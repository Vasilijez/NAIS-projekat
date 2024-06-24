package rs.ac.uns.acs.nais.ColumnarDatabaseService.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.ExpenseDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Expense;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.enumeration.ExpenseCategory;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    ExpenseMapper mapper = Mappers.getMapper(ExpenseMapper.class);

    @Mappings({
            @Mapping(target = "category", source = "expense.category", qualifiedByName = "categoryToString"),
            @Mapping(target = "expenseCreationTimestamp", source = "expense.expenseCreationTimestamp"),
            @Mapping(target = "description", source = "expense.description"),
            @Mapping(target = "amount", source = "expense.amount")
    })
    ExpenseDTO expenseToExpenseDTO(Expense expense);

    @Named("categoryToString")
    default String mapCategoryToString(ExpenseCategory category) {
        return category != null ? category.name() : null;
    }
}
