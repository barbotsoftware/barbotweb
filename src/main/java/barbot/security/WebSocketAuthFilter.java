package barbot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Alex on 5/1/2017.
 */
public class WebSocketAuthFilter implements Filter{

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        // Get the spring security context associated with the current session
        SecurityContext securityContext = (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");

        if(securityContext == null || (securityContext.getAuthentication() == null || !securityContext.getAuthentication().isAuthenticated())) {
            // User has not been authenticated, attempt to authenticate
            if(request.getParameter("username") != null && request.getParameter("password") != null) {
                Authentication authRequest = new UsernamePasswordAuthenticationToken(request.getParameter("username"), request.getParameter("password"));
                Authentication authResponse = null;
                try {
                    authResponse = authenticationManager.authenticate(authRequest);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if(authResponse != null && authResponse.isAuthenticated()) {
                    SecurityContextHolder.getContext().setAuthentication(authResponse);
                }
            } else if (request.getParameter("barbot_id") != null && request.getParameter("password") != null) {
                Authentication authRequest = new BarbotAuthenticationToken(request.getParameter("barbot_id"), request.getParameter("password"));
                Authentication authResponse = null;
                try {
                    authResponse = authenticationManager.authenticate(authRequest);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if(authResponse != null && authResponse.isAuthenticated()) {
                    SecurityContextHolder.getContext().setAuthentication(authResponse);
                    request.getSession().setAttribute("barbot", authResponse.getPrincipal());
                }
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) {

    }
}