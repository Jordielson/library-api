package edu.bd2.projeto1.api_biblioteca.security.util;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import edu.bd2.projeto1.api_biblioteca.models.Usuario;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${library.jwtSecret}")
    private String jwtSecret;

    @Value("${library.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {
        Usuario userPrincial = (Usuario) authentication.getPrincipal();

        return Jwts.builder()
            .setSubject((userPrincial.getCpf()))
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Assinatura JWT invalida: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Token JWT invalido: {}", e.getMessage());
        }catch (ExpiredJwtException e) {
            logger.error("Token JWT expirou: {}", e.getMessage());
        }catch (UnsupportedJwtException e) {
            logger.error("Token JWT nao eh suportado: {}", e.getMessage());
        }catch (IllegalArgumentException e) {
            logger.error("Claims JWT vazio: {}", e.getMessage());
        }

        return false;
    }
}
