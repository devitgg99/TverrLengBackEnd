package org.example.tverleng.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.example.tverleng.model.entities.AppUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60; //5 hour
    public static final String SECRET = "dnDmZQFcW2OA7p4XnflcbPlV1JjGogT11NlCU2/945FcVOP1sT+6e1Q6W5TrmeFGx3kr4uVv0FUCRhFcrS/Xww==";

    private String createToken(Map<String, Object> claim, String subject) {
        return Jwts.builder()
                .claims(claim)
                .subject(subject)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(getSignKey()).compact();
    }

    private SecretKey getSignKey() {
        byte[] keyBytes = Base64.getDecoder().decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //2. generate token for user
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        AppUser appUser = (AppUser) userDetails;
        return createToken(claims,appUser.getEmail());
    }

    //3. retrieving any information from token we will need the secret key
    private Claims extractAllClaim(String token) {
        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    //4. extract a specific claim from the JWT token’s claims.
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaim(token);
        return claimsResolver.apply(claims);
    }

    //5. retrieve username from jwt token
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //6. retrieve expiration date from jwt token
    public Date extractExpirationDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    //7. check expired token
    private Boolean isTokenExpired(String token) {
        return extractExpirationDate(token).before(new Date());
    }

    //8. validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String email = extractEmail(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}