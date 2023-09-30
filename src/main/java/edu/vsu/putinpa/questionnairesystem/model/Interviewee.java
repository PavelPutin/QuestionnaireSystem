package edu.vsu.putinpa.questionnairesystem.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Entity(name = "interviewee")
@Data
@NoArgsConstructor
public class Interviewee {
    enum Gender {male, female}
    enum MaritalStatus {married, divorced, was_not_married}

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    private int age;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    @Enumerated(value = EnumType.STRING)
    private MaritalStatus maritalStatus;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Country country;

    @OneToOne
    @JoinColumn(referencedColumnName = "username")
    private Principal principal;
}
