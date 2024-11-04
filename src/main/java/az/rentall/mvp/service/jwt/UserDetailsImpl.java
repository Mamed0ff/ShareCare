//package az.rentall.mvp.service.jwt;
//
//import az.rentall.mvp.model.entity.UserEntity;
//import az.rentall.mvp.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class UserDetailsImpl implements UserDetailsService {
//    private final UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        UserEntity user =userRepository.findByEmail(email)
//                .orElseThrow(()->new UsernameNotFoundException("USER_NOT_FOUND"));
//        Set<String> authorities = new HashSet<>();
//        authorities.add(user.getRoleType().name());
//        return new User(
//                user.getEmail(),
//                user.getPassword(),
//                authorities.stream()
//                        .map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList()));
//    }
//}
