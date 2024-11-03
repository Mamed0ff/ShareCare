package az.rentall.mvp.service.impl;

import az.rentall.mvp.exception.NotFoundException;
import az.rentall.mvp.model.entity.UserEntity;
import az.rentall.mvp.repository.UserRepository;
import az.rentall.mvp.security.UserPrincipal.UserPrincipal;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);
        Set<GrantedAuthority> authorities = new HashSet<>();
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            UserPrincipal userPrincipal = new UserPrincipal();
            userPrincipal.setUsername(user.getEmail());
            userPrincipal.setPassword(user.getPassword());
            authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRoleType().name()));
            userPrincipal.setAuthorities(authorities);
            return userPrincipal;
        } else {
            throw new NotFoundException(email + " not found");
        }

    }
}
