package edu.vsu.putinpa.questionnairesystem.model;

import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;

import java.util.List;
import java.util.UUID;

@Entity(name = "interviewee")
@Data
@NoArgsConstructor
public class Interviewee {
    public enum Gender {male, female}
    public enum MaritalStatus {married, divorced, was_not_married}

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private Principal principal;

    @OneToMany(mappedBy = "interviewee")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Choice> choices;
}
