package edu.vsu.putinpa.questionnairesystem.app.mapper;

import edu.vsu.putinpa.questionnairesystem.api.dto.response.QuestionnaireBriefDTO;
import edu.vsu.putinpa.questionnairesystem.item.model.Questionnaire;
import edu.vsu.putinpa.questionnairesystem.item.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface QuestionnaireMapper {

    @Mapping(source = "answered", target = "answeredAmount", qualifiedByName = "getAnsweredAmount")
    @Mapping(source = "author", target = "authorName", qualifiedByName = "getAuthorName")
    List<QuestionnaireBriefDTO> toDto(List<Questionnaire> questionnaire);

    @Named("getAnsweredAmount")
    static int getAnsweredAmount(List<User> answered) {
        return answered.size();
    }

    @Named("getAuthorName")
    static String getAuthorName(User author) {
        return author.getUsername();
    }
}
