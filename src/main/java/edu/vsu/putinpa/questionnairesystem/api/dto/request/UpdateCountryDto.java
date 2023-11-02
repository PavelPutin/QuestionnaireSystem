package edu.vsu.putinpa.questionnairesystem.api.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UpdateCountryDto {
    @Length(min = 1, max = 64)
    String name;
}
