package edu.vsu.putinpa.questionnairesystem.item;

import edu.vsu.putinpa.questionnairesystem.item.model.Questionnaire;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface QuestionnairesRepository extends
        JpaRepository<Questionnaire, UUID>,
        JpaSpecificationExecutor<Questionnaire> {
    Optional<Questionnaire> findByName(String name);

    void deleteByName(String name);

    static Specification<Questionnaire> nameContains(String name) {
        return (questionnaire, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(questionnaire.get("name"), "%" + name + "%");
    }

    static Specification<Questionnaire> hasAuthor(String authorName) {
        return (questionnaire, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(questionnaire.get("author").get("username"), "%" + authorName + "%");
    }
}
