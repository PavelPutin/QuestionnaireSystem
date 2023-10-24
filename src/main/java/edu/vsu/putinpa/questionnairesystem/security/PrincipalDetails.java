package edu.vsu.putinpa.questionnairesystem.security;

import edu.vsu.putinpa.questionnairesystem.model.Principal;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public record PrincipalDetails(Principal principal) implements UserDetails {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
        if (principal.getAuthor() != null) {
            authority = new SimpleGrantedAuthority("ROLE_AUTHOR");
        }

        if (principal.getInterviewee() != null) {
            authority = new SimpleGrantedAuthority("ROLE_INTERVIEWEE");
        }

        return Collections.singleton(authority);
    }

    @Override
    public String getPassword() {
        return principal.getPassword();
    }

    @Override
    public String getUsername() {
        return principal.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
