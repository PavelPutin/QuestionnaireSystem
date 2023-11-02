package edu.vsu.putinpa.questionnairesystem.item.model;

import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;

import java.util.List;
import java.util.UUID;

@Entity(name = "user_table")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String username;
    private String password;

    private int age;

    @Enumerated(value = EnumType.STRING)
    @Column(columnDefinition = "gender_t")
    @Type(PostgreSQLEnumType.class)
    private Gender gender;

    @Enumerated(value = EnumType.STRING)
    @Column(columnDefinition = "marital_status_t")
    @Type(PostgreSQLEnumType.class)
    private MaritalStatus maritalStatus;

    @ManyToOne
    @JoinColumn(name = "country", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Country country;

    @OneToMany
    private List<Questionnaire> written;

    @ManyToMany
    @JoinTable(
            name = "answered",
            joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "questionnaire_id"))
    private List<Questionnaire> answered;
}
