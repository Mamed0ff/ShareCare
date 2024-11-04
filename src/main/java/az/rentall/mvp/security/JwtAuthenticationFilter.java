package az.rentall.mvp.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    public static final String BEARER = "Bearer ";
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("Custom filter started");
        String token = request.getHeader("Authorization");
        log.info("token:{}", token);
        if (token != null && token.startsWith(BEARER)) {
            String tokenWithoutBearer = token.substring(BEARER.length());
            Claims claims = jwtTokenProvider.tokenParser(tokenWithoutBearer);
            log.info("claims:{}",claims);
            Date date = jwtTokenProvider.extractClaim(tokenWithoutBearer, Claims::getExpiration);
            if (date.before(new Date())) return;
            String username = jwtTokenProvider.extractClaim(tokenWithoutBearer, Claims::getSubject);
            Authentication authentication = jwtTokenProvider.getAuthentication(username);
            if(authentication!=null){
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }else{
                System.out.println("User is not auth");
            }


        }
        log.info("Response is:{}",response.getHeader("Authorization"));
        log.info("Request is:{}",request.getHeader("Authorization"));
//        log.info("TEST:{}",SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        filterChain.doFilter(request, response);
    }


}
