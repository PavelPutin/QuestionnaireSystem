package edu.vsu.putinpa.questionnairesystem.model;

import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.UUID;

@Entity(name = "choice")
@Data
@NoArgsConstructor
public class Choice {
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
    private Option option;
}
