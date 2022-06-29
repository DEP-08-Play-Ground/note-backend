package lk.ijse.dep8.note;

import lk.ijse.dep8.note.entity.User;
import lk.ijse.dep8.note.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authorization.AuthorityAuthorizationDecision;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.Optional;

@SpringBootApplication
public class AppInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AppInitializer.class, args);
    }

    @Bean
    public UserDetailsService customUserDetailsService(UserRepository userRepository) {

        return new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//                switch (username) {
//                    case "dulanga":
//                        return new User(username, "{noop}123456", new ArrayList<>());
//                    case "upea":
//                        return new User(username, "{noop}456678", new ArrayList<>());
//                }
//                return null;
                Optional<User> optUser = userRepository.findUserByEmail(username);
                return optUser.map(user -> new org.springframework.security.core.userdetails.User(username, "{noop}" + user.getPassword(), new ArrayList<>())).orElse(null);

            }
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, UserRepository userRepository) throws Exception {
        return http
                .csrf().disable()
//                .authorizeRequests()
                .authorizeHttpRequests(authManager -> {
                    authManager.antMatchers("/api/v1/users/{userId}/**")
                            .access((authentication, object) -> {
                                org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) (authentication.get().getPrincipal());
                                Optional<User> optUser = userRepository.findUserByEmail(user.getUsername());
                                if (optUser.isPresent()) {
                                    if (optUser.get().getId().equals(object.getVariables().get("userId"))) {
                                        return new AuthorizationDecision(true);
                                    }
                                }
                                return new AuthorizationDecision(false);

                            });
                })
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.POST, "/api/v1/users")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .build();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
