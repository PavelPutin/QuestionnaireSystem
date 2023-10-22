package edu.vsu.putinpa.questionnairesystem.service;


import edu.vsu.putinpa.questionnairesystem.dto.request.AuthorRegistrationDTO;
import edu.vsu.putinpa.questionnairesystem.dto.request.IntervieweeRegistrationDTO;
import edu.vsu.putinpa.questionnairesystem.exception.ValidationException;
import edu.vsu.putinpa.questionnairesystem.model.Author;
import edu.vsu.putinpa.questionnairesystem.model.Country;
import edu.vsu.putinpa.questionnairesystem.model.Interviewee;
import edu.vsu.putinpa.questionnairesystem.model.Principal;
import edu.vsu.putinpa.questionnairesystem.repository.AuthorsRepository;
import edu.vsu.putinpa.questionnairesystem.repository.CountriesRepository;
import edu.vsu.putinpa.questionnairesystem.repository.IntervieweesRepository;
import edu.vsu.putinpa.questionnairesystem.repository.PrincipalsRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class RegistrationService {
    private final PrincipalsRepository principalsRepository;
    private final AuthorsRepository authorsRepository;
    private final PasswordEncoder passwordEncoder;
    private final IntervieweesRepository intervieweesRepository;
    private final CountriesRepository countriesRepository;

    public RegistrationService(PrincipalsRepository principalsRepository, AuthorsRepository authorsRepository, PasswordEncoder passwordEncoder, IntervieweesRepository intervieweesRepository, CountriesRepository countriesRepository) {
        this.principalsRepository = principalsRepository;
        this.authorsRepository = authorsRepository;
        this.passwordEncoder = passwordEncoder;
        this.intervieweesRepository = intervieweesRepository;
        this.countriesRepository = countriesRepository;
    }

    public void registerAuthor(AuthorRegistrationDTO authorRegistration, Errors errors) {
        Principal principal = principalsRepository.getPrincipalByUsername(authorRegistration.username())
                .orElseGet(() -> {
                    Principal toReturn = new Principal();
                    toReturn.setUsername(authorRegistration.username());
                    toReturn.setPassword(passwordEncoder.encode(authorRegistration.password()));
                    return toReturn;
                });

        if (errors.hasErrors()) {
            throw new ValidationException(errors);
        }

        Author author = new Author();
        author.setPrincipal(principal);
        principal.setAuthor(author);

        authorsRepository.save(author);
    }

    public void registerInterviewee(IntervieweeRegistrationDTO intervieweeRegistration, Errors errors) {
        Principal principal = principalsRepository.getPrincipalByUsername(intervieweeRegistration.username())
                .orElseGet(() -> {
                    Principal toReturn = new Principal();
                    toReturn.setUsername(intervieweeRegistration.username());
                    toReturn.setPassword(passwordEncoder.encode(intervieweeRegistration.password()));
                    return toReturn;
                });

        if (errors.hasErrors()) {
            throw new ValidationException(errors);
        }

        Interviewee interviewee = new Interviewee();
        interviewee.setPrincipal(principal);
        principal.setInterviewee(interviewee);
        interviewee.setAge(intervieweeRegistration.age());

        Country country = countriesRepository.findById(intervieweeRegistration.country()).get();
        interviewee.setCountry(country);

        Interviewee.MaritalStatus maritalStatus = Interviewee.MaritalStatus.valueOf(intervieweeRegistration.maritalStatus());
        interviewee.setMaritalStatus(maritalStatus);

        Interviewee.Gender gender = Interviewee.Gender.valueOf(intervieweeRegistration.gender());
        interviewee.setGender(gender);

        intervieweesRepository.save(interviewee);
    }
}
