package barbot.security;

import barbot.database.model.Barbot;
import barbot.database.service.BarbotService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by Alex on 5/18/2017.
 */
public class BarbotAuthenticationProvider implements AuthenticationProvider {

    private BarbotService barbotService;

    private BCryptPasswordEncoder passwordEncoder;

    public void setBarbotService(BarbotService barbotService) {
        this.barbotService = barbotService;
    }

    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Authentication authenticate(Authentication authentication) {
        String barbotId = authentication.getName();
        String password = authentication.getCredentials().toString();

        Barbot barbot = barbotService.findByUid(barbotId);
        if(barbot != null) {
            if(passwordEncoder.matches(password, barbot.getPassword())) {
                BarbotAuthenticationToken authenticationToken = new BarbotAuthenticationToken(barbot, password);
                authenticationToken.setAuthenticated(true);
                return authenticationToken;
            }
        }

        return null;
    }

    public boolean supports(Class<?> authentication) {
        return authentication.equals(BarbotAuthenticationToken.class);
    }
}
