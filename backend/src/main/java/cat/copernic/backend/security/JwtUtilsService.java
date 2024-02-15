package cat.copernic.backend.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class JwtUtilsService {

    private  Logger logger = LoggerFactory.getLogger(JwtUtilsService.class);
    
    @Value("${api.data.auth.jwt-key}")
    private String apiKey;
    
    // Token expiration time in ms, in this case 1800000 => 30min | 3600000 => 1h |  7200000 => 2h
    public final long tokenExpiration = 7200000;

    // Generates a new Token based in the user details
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        var rol = userDetails.getAuthorities().stream().collect(Collectors.toList()).get(0);
        claims.put("rol", rol);
        return createToken(claims, userDetails.getUsername());
    }

    // Creates the token with all the necesary data
    private String createToken(Map<String, Object> claims, String subject) {
        Algorithm algorithm = Algorithm.HMAC256(this.apiKey);

        Date now = new Date(System.currentTimeMillis());

        Builder token = JWT.create()
                           .withSubject(subject)
                           .withClaim("rol", claims.get("rol").toString())
                           .withExpiresAt(new Date(now.getTime() + this.tokenExpiration));

        return token.sign(algorithm);
    }

    // Validate the token
    public boolean validateToken(String token, UserDetails userDetails) {
        // Validate the token Signature
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.apiKey);
            JWTVerifier verifier = JWT.require(algorithm).build();
    
            verifier.verify(token);
        } catch (Exception ex) {
            logger.error("Invalid Token Signatur");
            return false;
        }

        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // Check if the token is expired
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date(System.currentTimeMillis()));
    }

    // Extract data from the JWT Token ----------------------------------------
    
    public Date extractExpiration(String token) throws JWTDecodeException {
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getExpiresAt();
    }
    
    public String extractUsername(String token) throws JWTDecodeException {
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getSubject();
    }

    public String extractRol(String token)  throws JWTDecodeException {
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getClaim("rol").toString();
    } 

}
