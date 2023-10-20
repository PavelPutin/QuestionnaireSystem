package edu.vsu.putinpa.questionnairesystem.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity(name = "choice")
@Data
@NoArgsConstructor
public class Choice {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "questionnaire_id", referencedColumnName = "id")
    private Questionnaire questionnaire;

    @ManyToOne
    @JoinColumn(name = "interviewee_id", referencedColumnName = "id")
    private Interviewee interviewee;

    @ManyToMany
    @JoinTable(
            name = "option_choice",
            joinColumns = @JoinColumn(name = "choice_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id"))
    private List<Option> options;
}
