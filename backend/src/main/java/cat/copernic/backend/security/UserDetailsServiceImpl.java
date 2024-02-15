package cat.copernic.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cat.copernic.backend.data.models.user.UserService;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        cat.copernic.backend.data.models.user.User user = userService.getByEmail(email);

        if(user != null && user.getIsEnabled()) {
            return User.withUsername(user.getEmail())
                    .password(user.getPassword())
                    .roles(user.getUserRole().toString())
                    .build();
        }

        throw new UsernameNotFoundException("User Not Found in the Database");
    }

}
