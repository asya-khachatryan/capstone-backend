package edu.aua.auth.config.security;


import edu.aua.auth.config.AppProperties;
import edu.aua.auth.service.user.model.JWTUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JWTTokenProvider {


    private final AppProperties appProperties;

    public JWTTokenProvider(AppProperties appProperties) {
        this.appProperties = appProperties;
    }


    public String createToken(final Authentication authentication) {
        final JWTUser userPrincipal = (JWTUser) authentication.getPrincipal();
        final Date now = new Date();
        final Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationMsec());

        return Jwts.builder()
                .claim("roles", authentication.getAuthorities())
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret())
                .compact();
    }

    public Long getUserIdFromToken(final String token) {
        final Claims claims = Jwts.parser()
                .setSigningKey(appProperties.getAuth().getTokenSecret())
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public String getUsername(String token) {
        return Jwts.parser()
                .setSigningKey(appProperties.getAuth().getTokenSecret())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(final String authToken) {
        try {
            Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(authToken);
            return true;
        } catch (final MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (final ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (final UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (final IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }

}
