package barbot.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import barbot.database.service.BarbotService;
import barbot.database.service.UserServiceImpl;
import barbot.security.BarbotAuthenticationProvider;
import barbot.security.WebSocketAuthFilter;

/**
 * Created by Naveen on 3/26/17.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    BarbotService barbotService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/auth/register").permitAll()
                .and().csrf().disable();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userService());

        BarbotAuthenticationProvider barbotAuthenticationProvider = new BarbotAuthenticationProvider();
        barbotAuthenticationProvider.setBarbotService(barbotService);
        barbotAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        List<AuthenticationProvider> providerList = new ArrayList();
        providerList.add(daoAuthenticationProvider);
        providerList.add(barbotAuthenticationProvider);

        return new ProviderManager(providerList);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserServiceImpl userService() {
        return new UserServiceImpl();
    }

    @Bean
    public FilterRegistrationBean WebSocketAuthFilterBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(webSocketAuthFilter());
        registrationBean.addUrlPatterns("/ws");
        registrationBean.setName("WebSocketAuthFilter");
        return registrationBean;
    }

    @Bean
    public WebSocketAuthFilter webSocketAuthFilter() {
        return new WebSocketAuthFilter();
    }
}
