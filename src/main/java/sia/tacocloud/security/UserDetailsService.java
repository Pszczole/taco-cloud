package sia.tacocloud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import sia.tacocloud.data.UserRepository;
import sia.tacocloud.model.User;

public interface UserDetailsService {

    //This method accepts a username and uses it to look up a
    //UserDetails object, if user username is not found it will throw the Exception
    UserDetails loadUserByUsername(String username) throws
            UsernameNotFoundException;

    @Bean
    public default UserDetailsService userDetailsService(UserRepository userRepository){
        return username -> {
            User user = userRepository.findByUsername(username);
            if(user != null) return user;

            throw new UsernameNotFoundException("User :"+username+" not found");
        };
    }

    @Bean
    public default SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .authorizeRequests()
                .antMatchers("/design","/orders").access("hasRole('USER')")
                .antMatchers("/","/**").access("permitAll()")
                .and().formLogin().loginPage("/login").and().build();
    }

}
