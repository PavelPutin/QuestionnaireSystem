package edu.vsu.putinpa.questionnairesystem.app.mapper;

import edu.vsu.putinpa.questionnairesystem.api.dto.response.QuestionnaireBriefDTO;
import edu.vsu.putinpa.questionnairesystem.api.dto.response.QuestionnaireDTO;
import edu.vsu.putinpa.questionnairesystem.item.model.Questionnaire;
import edu.vsu.putinpa.questionnairesystem.item.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = OptionMapper.class)
public interface QuestionnaireMapper {
    List<QuestionnaireBriefDTO> toDto(List<Questionnaire> questionnaire);

    @Mapping(target = "answeredAmount", expression="java(questionnaire.getAnswered().size())")
    @Mapping(target = "authorName", expression="java(questionnaire.getAuthor().getUsername())")
    QuestionnaireBriefDTO toBriefDto(Questionnaire questionnaire);

    @Mapping(target = "authorName", expression="java(questionnaire.getAuthor().getUsername())")
    QuestionnaireDTO toDto(Questionnaire questionnaire);
}
