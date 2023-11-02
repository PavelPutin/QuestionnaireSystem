package edu.vsu.putinpa.questionnairesystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity(name = "country")
@Data
@NoArgsConstructor
public class Country {
    @Id
    @Length(min = 2, max = 2)
    private String id;
    @Length(min = 1, max = 64)
    private String value;
    @OneToMany(mappedBy = "country")
    private List<User> users;
    @OneToMany(mappedBy = "country")
    private List<Choice> choices;
}
