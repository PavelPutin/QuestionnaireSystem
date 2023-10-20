package edu.vsu.putinpa.questionnairesystem.repository;

import edu.vsu.putinpa.questionnairesystem.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountriesRepository extends JpaRepository<Country, String> {
}
