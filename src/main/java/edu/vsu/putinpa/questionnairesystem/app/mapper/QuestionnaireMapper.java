package edu.vsu.putinpa.questionnairesystem.app.mapper;

import edu.vsu.putinpa.questionnairesystem.api.dto.request.QuestionnaireCreationDto;
import edu.vsu.putinpa.questionnairesystem.api.dto.response.QuestionnaireBriefDto;
import edu.vsu.putinpa.questionnairesystem.api.dto.response.QuestionnaireDto;
import edu.vsu.putinpa.questionnairesystem.item.model.Questionnaire;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = OptionMapper.class)
public interface QuestionnaireMapper {
    List<QuestionnaireBriefDto> toDto(List<Questionnaire> questionnaire);

    @Mapping(target = "answeredAmount", expression="java(questionnaire.getAnswered().size())")
    @Mapping(target = "authorName", source="questionnaire.author.username")
    QuestionnaireBriefDto toBriefDto(Questionnaire questionnaire);

    @Mapping(target = "authorName", source="questionnaire.author.username")
    @Mapping(target = "authorId", source="questionnaire.author.id")
    QuestionnaireDto toDto(Questionnaire questionnaire);

    Questionnaire toQuestionnaire(QuestionnaireCreationDto creationDTO);
}
