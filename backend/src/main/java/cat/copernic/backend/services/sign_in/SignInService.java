package cat.copernic.backend.services.sign_in;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cat.copernic.backend.data.dto.authentication.SignInDTO;
import cat.copernic.backend.data.responses.Response;
import cat.copernic.backend.data.responses.ResponseState;
import cat.copernic.backend.security.JwtUtilsService;

@Service
public class SignInService {

    @Autowired 
    private AuthenticationManager authManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtilsService jwtUtilsService;


    public Response signIn(SignInDTO body) {

        try {
            authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    body.getEmail(),
                    body.getPassword()
                )
            );
    
            UserDetails userDetails = userDetailsService.loadUserByUsername(body.getEmail());
    
            String token = jwtUtilsService.generateToken(userDetails);
    
            return new Response(
                ResponseState.OK,
                String.format("{\"token\": \"%s\"}", token)
            );

        } catch (UsernameNotFoundException ex) {
            return new Response(
                ResponseState.ERROR,
                "{\"credentials error\": \"User not found\"}"
            );

        } catch (Exception ex) {
            return new Response(
                ResponseState.ERROR,
                String.format("{\"error\": \"%s\"}", ex.getMessage())
            );

        }

    }

}
