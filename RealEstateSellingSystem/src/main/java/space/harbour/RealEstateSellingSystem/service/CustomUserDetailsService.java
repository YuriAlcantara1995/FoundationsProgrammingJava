package space.harbour.RealEstateSellingSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import space.harbour.RealEstateSellingSystem.domain.CustomUserDetails;
import space.harbour.RealEstateSellingSystem.domain.User;
import space.harbour.RealEstateSellingSystem.repository.UserRepository;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    @Autowired
    private RealtorService realtorService;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(usernameOrEmail);
        if(user != null){
            return new CustomUserDetails(user, realtorService);
        }else {
            throw new UsernameNotFoundException("Invalid email or password");
        }
    }
}