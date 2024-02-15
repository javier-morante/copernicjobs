package cat.copernic.backend.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import cat.copernic.backend.security.filters.CorsFilter;
import cat.copernic.backend.security.filters.JwtRequestFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private CorsFilter corsFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .csrf(
            (csrf) -> {csrf.disable();}
        )
        .authorizeHttpRequests(
            (req) -> {
                req
                // Access endpoints that have to be public
                .requestMatchers("/api/authentication/**").permitAll()
                .requestMatchers("/api/password-recovery/**").permitAll()
                .requestMatchers("/api/user-profile/**").hasAnyRole("STUDENT", "COMPANY", "ADMINISTRATOR")
                .requestMatchers("/api/register/**").permitAll()
                .requestMatchers("/api/nav-bar/**").hasAnyRole("STUDENT", "COMPANY", "ADMINISTRATOR")
                .requestMatchers("/api/role_management/**").hasAnyRole("ADMINISTRATOR")
                .requestMatchers("/api/offers/**").hasAnyRole("STUDENT", "COMPANY", "ADMINISTRATOR")
                .requestMatchers("/api/company/**").hasAnyRole("STUDENT", "COMPANY", "ADMINISTRATOR")
                .requestMatchers("/api/upload/**").hasAnyRole("STUDENT", "COMPANY", "ADMINISTRATOR")
                .requestMatchers("/api/download/**").permitAll()
                .requestMatchers("/api/incidence/**").permitAll()
                .requestMatchers("/api/images/**").permitAll()
                .requestMatchers("/api/user-management/**").hasAnyRole("ADMINISTRATOR")
                .requestMatchers("/api/profesional-profile/**").hasAnyRole("STUDENT")
                .requestMatchers("/api/reports/**").hasAnyRole("ADMINISTRATOR")
                .anyRequest().authenticated();
            }
        )
        .cors(
            (cors) -> {cors.disable();}
        )
        .addFilterBefore(
            jwtRequestFilter,
            UsernamePasswordAuthenticationFilter.class
        ).addFilterBefore(
            corsFilter,
            JwtRequestFilter.class
        )
        .sessionManagement(
            (session) -> {
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            }
        );

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
