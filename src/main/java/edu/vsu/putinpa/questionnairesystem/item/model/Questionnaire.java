package edu.vsu.putinpa.questionnairesystem.item.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Entity(name = "questionnaire")
@Data
@NoArgsConstructor
public class Questionnaire {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String question;
    private boolean multiple;

    @ManyToMany(mappedBy = "answered")
    private List<User> answered;

    @ManyToOne
    private User author;

    @OneToMany(mappedBy = "questionnaire")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Option> options;
}
