package edu.vsu.putinpa.questionnairesystem.item;

import edu.vsu.putinpa.questionnairesystem.item.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountriesRepository extends JpaRepository<Country, String> {
}
