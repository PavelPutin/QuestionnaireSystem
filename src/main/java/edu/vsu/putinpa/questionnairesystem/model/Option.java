package edu.vsu.putinpa.questionnairesystem.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity(name = "option")
@Data
@NoArgsConstructor
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String text;
    @ManyToOne
    @JoinColumn(name = "questionnaire_id", referencedColumnName = "id")
    private Questionnaire questionnaire;

    @ManyToMany(mappedBy = "options")
    private List<Choice> choices;
}
