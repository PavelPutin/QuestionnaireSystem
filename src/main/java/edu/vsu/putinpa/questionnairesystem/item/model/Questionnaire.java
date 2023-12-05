package edu.vsu.putinpa.questionnairesystem.item.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
    @Fetch(FetchMode.JOIN)
    private List<User> answered;

    @Transient
    private int answeredCount;

    @ManyToOne
    private User author;

    @OneToMany(mappedBy = "questionnaire")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Option> options;

    @PostLoad
    public void setAnsweredCount() {
        answeredCount = answered.size();
    }
}
