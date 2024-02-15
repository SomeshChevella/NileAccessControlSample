package edu.scranton.cs.se518.nile.security;

import edu.scranton.cs.se518.nile.models.User;
import edu.scranton.cs.se518.nile.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * NileUserDetailsService handles looking up user details in response to
 * an authentication request. By implementing the Spring Security
 * UserDetailsService interface we can hook our custom user type into
 * the standard Spring Security authentication flow.
 */
@Service
public class NileUserDetailsService implements UserDetailsService {

    @Autowired	
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new NilePrincipal(user);
    }
}