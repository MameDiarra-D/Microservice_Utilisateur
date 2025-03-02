package uasz.sn.microservice_utilisateur.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils {

    @Value("${app.secret-key}")
    private String secretKey;

    @Value("${app.expiration-time}")
    private Long expirationTime;

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        Date expiryDate = Date.from(Instant.ofEpochMilli(System.currentTimeMillis() + expirationTime));

        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secretKey), SignatureAlgorithm.HS256.getJcaName());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expiryDate)
                .signWith(hmacKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String bearerToken) {
        return extractClaim(cleanToken(bearerToken), Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Jws<Claims> jwsClaims = extractClaims(token);
        return claimsResolver.apply(jwsClaims.getBody());
    }

    private Jws<Claims> extractClaims(String token) {
        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secretKey), SignatureAlgorithm.HS256.getJcaName());

        return Jwts.parserBuilder()
                .setSigningKey(hmacKey)
                .build()
                .parseClaimsJws(token);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiry(token).before(new Date());
    }

    public Date extractExpiry(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private String cleanToken(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        throw new IllegalArgumentException("Token invalide !");
    }
}
