package edu.vsu.putinpa.questionnairesystem.app.mapper;

import edu.vsu.putinpa.questionnairesystem.api.dto.response.CountryDTO;
import edu.vsu.putinpa.questionnairesystem.app.service.CountryService;
import edu.vsu.putinpa.questionnairesystem.item.model.Country;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = CountryService.class)
public interface CountryMapper {
    CountryDTO toCountryDto(Country country);
    List<CountryDTO> toCountryDto(List<Country> countries);
}
