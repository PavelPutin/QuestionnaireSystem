package edu.vsu.putinpa.questionnairesystem.app.mapper;

import edu.vsu.putinpa.questionnairesystem.api.dto.response.OptionDTO;
import edu.vsu.putinpa.questionnairesystem.item.model.Choice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OptionMapper {
    @Mapping(source = "choices", target = "choicesAmount", qualifiedByName = "getChoicesAmount")
    OptionDTO toDto(OptionDTO optionDTO);

    @Named("getChoicesAmount")
    static int getChoicesAmount(List<Choice> choices) {
        return choices.size();
    }
}
