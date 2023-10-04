package edu.vsu.putinpa.questionnairesystem.security;

import edu.vsu.putinpa.questionnairesystem.model.Principal;
import edu.vsu.putinpa.questionnairesystem.repository.PrincipalsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PrincipalDetailsService implements UserDetailsService {
    private final PrincipalsRepository repository;

    @Autowired
    public PrincipalDetailsService(PrincipalsRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Principal> optionalPrincipal = repository.getPrincipalByUsername(username);
        if (optionalPrincipal.isPresent()) {
            return new PrincipalDetails(optionalPrincipal.get());
        }
        throw new UsernameNotFoundException("Username '%s' not found.".formatted(username));
    }
}
