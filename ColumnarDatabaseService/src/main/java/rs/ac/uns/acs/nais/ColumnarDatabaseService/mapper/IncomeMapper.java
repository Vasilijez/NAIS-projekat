package rs.ac.uns.acs.nais.ColumnarDatabaseService.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.Mapping;

import org.mapstruct.factory.Mappers;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.IncomeDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Income;

@Mapper(componentModel = "spring")
public interface IncomeMapper {


    IncomeMapper mapper = Mappers.getMapper(IncomeMapper.class);

    @Mappings({
            @Mapping(target = "incomeCreationTimestamp", source = "income.incomeCreationTimestamp"),
            @Mapping(target = "description", source = "income.description"),
            @Mapping(target = "amount", source = "income.amount"),
            @Mapping(target = "source", source = "income.source"),

    })
    IncomeDTO incomeToincomeDTO (Income income);

}
