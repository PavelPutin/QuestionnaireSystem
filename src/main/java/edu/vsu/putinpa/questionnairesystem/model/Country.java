package edu.vsu.putinpa.questionnairesystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "country")
@Data
@NoArgsConstructor
public class Country {
    @Id
    private String id;
    private String value;
    @OneToMany(mappedBy = "country")
    private List<User> users;
    @OneToMany(mappedBy = "country")
    private List<Choice> choices;
}
