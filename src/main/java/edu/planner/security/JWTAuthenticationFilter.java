package edu.planner.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.planner.dto.CredentialsDTO;
import edu.planner.enums.Profile;
import edu.planner.exception.ErrorCode;
import edu.planner.exception.NoPermissionsException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) {
        CredentialsDTO credentials;
        try {
            credentials = new ObjectMapper().readValue(req.getInputStream(), CredentialsDTO.class);
        } catch (IOException e) {
            throw new RuntimeException();
        }

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(credentials.getEmail(),
                credentials.getPassword(), new ArrayList<>());

        Authentication auth = authenticationManager.authenticate(authToken);

        UserSS userSS = (UserSS) auth.getPrincipal();
        if (userSS.hasAnyRole(Arrays.asList(Profile.ADMIN, Profile.COORDINATOR, Profile.TEACHER))) {
            return auth;
        } else {
            throw new NoPermissionsException(ErrorCode.USER_WAIT_FOR_APPROVAL);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) {
        String username = ((UserSS) auth.getPrincipal()).getUsername();
        String token = jwtUtil.generateToken(username);
        res.addHeader("Authorization", "Q9OZw9PEJQe4dDfkFQ98yhNaKC99ZHD5xm#SENqxBJZgS7z0$&$vSz6E7KVmJfa4" + token);
        res.addHeader("Permissions", auth.getAuthorities().stream().map(role -> role.toString()).collect(Collectors.joining(",")));
        res.addHeader("access-control-expose-headers", "Authorization, Permissions");
    }

    private static class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {

        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {

            response.setContentType("application/json");
            if (exception instanceof NoPermissionsException) {
                response.setStatus(HttpStatus.FORBIDDEN.value());
                response.getWriter().append(json(exception.getMessage()));
            } else {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.getWriter().append(json("Invalid email or password"));
            }
        }

        private String json(String message) {
            long date = new Date().getTime();
            return "{\"timestamp\": " + date + ", " + "\"status\": 401, " + "\"error\": \"Not authorized\", "
                    + "\"message\": \"" + message + "\", " + "\"path\": \"/login\"}";
        }
    }
}
