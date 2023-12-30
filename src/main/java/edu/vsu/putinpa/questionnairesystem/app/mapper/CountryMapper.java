package edu.vsu.putinpa.questionnairesystem.app.mapper;

import edu.vsu.putinpa.questionnairesystem.api.dto.response.CountryDto;
import edu.vsu.putinpa.questionnairesystem.app.service.CountryService;
import edu.vsu.putinpa.questionnairesystem.item.model.Country;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = CountryService.class)
public interface CountryMapper {
    CountryDto toCountryDto(Country country);
    List<CountryDto> toCountryDto(List<Country> countries);
}
