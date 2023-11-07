package edu.vsu.putinpa.questionnairesystem.app.mapper;

import edu.vsu.putinpa.questionnairesystem.api.dto.response.OptionDTO;
import edu.vsu.putinpa.questionnairesystem.item.model.Option;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OptionMapper {
    @Mapping(target = "choicesAmount", expression="java(option.getChoices().size())")
    OptionDTO toDto(Option option);
}
