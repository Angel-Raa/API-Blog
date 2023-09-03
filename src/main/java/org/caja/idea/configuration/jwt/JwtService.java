package org.caja.idea.configuration.jwt;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.caja.idea.entity.models.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    @Value("${spring.security.secrets.key}")
    private String SECRETE_KEY;
    @Value("${spring.jwt.expired}")
    private long EXPIRATION_IN_SECONDS_MILLIS;

    public String generateToken(Users username, Map<String, Object> claims) {
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(issuedAt.getTime() + EXPIRATION_IN_SECONDS_MILLIS * 60 * 1000);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username.getUsername())
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .signWith(generateKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key generateKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRETE_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Map<String, Object> generateExtraClaims(Users users) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("username", users.getUsername());
        extraClaims.put("email", users.getEmail());
        extraClaims.put("Role", users.getRole());
        extraClaims.put("Permission", users.getAuthorities());
        return extraClaims;
    }

    public String extractUsername(String jwt) {
        return extractAllClaims(jwt);
    }

    private String extractAllClaims(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(generateKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();
    }
}
