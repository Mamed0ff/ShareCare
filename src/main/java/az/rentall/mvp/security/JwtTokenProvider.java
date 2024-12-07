package az.rentall.mvp.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;



@Component
@Slf4j
@RequiredArgsConstructor
public class JwtTokenProvider {
    @Value("${jwt.secret-key}")
    private String secretKey;
    private final UserDetailsService userDetailsService;

    public Claims tokenParser(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) generateKey(secretKey))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Key generateKey(String secretKey) {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    public Authentication getAuthentication(String email) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsFunction) {
        Claims claims = tokenParser(token);
        return claimsFunction.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        Set<String> authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        return Jwts.builder()
                .signWith(generateKey(secretKey))
                .issuedAt(new Date())
                .expiration(Date.from(Instant.now().plus(Duration.ofDays(7))))
                .subject(userDetails.getUsername())
                .claim("authority",authorities)
                .compact();
    }
    public Boolean validateToken(String token){
        try{
            Jwts.parser().verifyWith((SecretKey) generateKey(secretKey)).build().parseSignedClaims(token);
            return true;
        }catch (ExpiredJwtException expiredJwtException){
            log.error("Jwt expired exception : {}", expiredJwtException.getMessage());
        }catch (IllegalArgumentException illegalArgumentException){
            log.error("JwtToken is null, empty or only whitespace : {}",illegalArgumentException.getMessage());
        }catch (MalformedJwtException malformedJwtException){
            log.error("Jwt is invalid : {}",malformedJwtException.getMessage());
        }catch (UnsupportedJwtException unsupportedJwtException){
            log.error("Jwt is not supported : {}",unsupportedJwtException.getMessage());
        }
        return false;
    }



}
