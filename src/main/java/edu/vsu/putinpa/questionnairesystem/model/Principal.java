package edu.vsu.putinpa.questionnairesystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "principal")
@Data
@NoArgsConstructor
public class Principal {
    @Id
    private String username;
    private String password;
}
