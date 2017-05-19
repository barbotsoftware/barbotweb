package barbot.security;

import barbot.database.model.Barbot;
import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * Created by Alex on 5/18/2017.
 */
public class BarbotAuthenticationToken extends AbstractAuthenticationToken {

    private Object principal;
    private Object credentials;

    public BarbotAuthenticationToken(Object principal, String password) {
        super(null);
        this.principal = principal;
        this.credentials = password;
    }

    public Object getPrincipal() {
        return principal;
    }

    public Object getCredentials() {
        return credentials;
    }
}
