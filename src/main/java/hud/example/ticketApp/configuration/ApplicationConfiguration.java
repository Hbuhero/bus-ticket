package hud.example.ticketApp.configuration;

import hud.example.ticketApp.model.Bus;
import hud.example.ticketApp.repo.AdminRepository;
import hud.example.ticketApp.repo.BusRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ApplicationConfiguration {

    @Autowired
    private final BusRepository repo;
    @Autowired
    private final AppUserDetailService userDetailService;

    @Autowired
    private final AdminRepository adminRepository;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public String createAndAddBusSeats(){
        List<Bus> buses = repo.findAll();
        buses.forEach(b -> b.setSeats(Bus.createBusSeats()));

        repo.saveAll(buses);
        return "Seats made";
    }

//    @Bean
//    public UserDetailsService getUserDetailsService(){
//        return username -> adminRepository.findByUsername(username)
//                .orElseThrow();
//    }

    @Bean
    public AuthenticationManager getAuthManager(){
        return new ProviderManager(getAuthProvider());
    }

    @Bean
    public AuthenticationProvider getAuthProvider(){
        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
        daoProvider.setPasswordEncoder(passwordEncoder());
        daoProvider.setUserDetailsService(userDetailService);
        return daoProvider;
    }
}
