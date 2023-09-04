package org.caja.idea.configuration.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.caja.idea.entity.models.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    @Value("${security.jwt.expiration}")
    private long EXPIRATION;

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;

    public String generateToken(Users user, Map<String, Object> claims) {
        Date issued = new Date(System.currentTimeMillis());
        Date expiration = new Date(issued.getTime() + (EXPIRATION * 60 * 1000));
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())                
                .setIssuedAt(issued)
                .setExpiration(expiration)
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .signWith(generatorKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    private Key generatorKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Map<String, Object> generateExtraClaims(Users user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("username", user.getUsername());
        extraClaims.put("email", user.getEmail());
        extraClaims.put("Role", user.getRole().name());
        extraClaims.put("permissions", user.getAuthorities());
        return extraClaims;
    }

    public String extractUsername(String jwt) {
        return extractAllClaims(jwt).getSubject();
    }

    private Claims extractAllClaims(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(generatorKey()).build()
                .parseClaimsJws(jwt).getBody();
    }

    public boolean isTokenValid(String token){
       try {
           return Jwts.parserBuilder()
                   .setSigningKey(generatorKey())
                   .build()
                   .parseClaimsJws(token)
                   .getBody()
                   .getExpiration()
                   .after(new Date());
       }catch (SignatureException e){
           System.out.println("JWT signature does not match locally computed signature. JWT validity cannot be asserted and should not be trusted.");
           return false;
       }
    }


}
