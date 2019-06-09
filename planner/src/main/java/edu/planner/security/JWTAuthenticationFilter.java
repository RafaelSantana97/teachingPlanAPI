package edu.planner.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.planner.dto.CredenciaisDTO;
import edu.planner.enums.Profile;
import edu.planner.exception.ErrorCode;
import edu.planner.exception.NoPermissionsException;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	private JWTUtil jwtUtil;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
		setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {

		try {
			CredenciaisDTO creds = new ObjectMapper().readValue(req.getInputStream(), CredenciaisDTO.class);

			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(creds.getEmail(),
					creds.getPassword(), new ArrayList<>());

			Authentication auth = authenticationManager.authenticate(authToken);

			UserSS userSS = (UserSS) auth.getPrincipal();
			if (userSS.hasRole(Profile.ADMIN) || userSS.hasRole(Profile.COORDINATOR)
					|| userSS.hasRole(Profile.TEACHER)) {
				return auth;
			} else {
				throw new NoPermissionsException(ErrorCode.USER_WAIT_FOR_APPROVAL);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		String username = ((UserSS) auth.getPrincipal()).getUsername();
		String token = jwtUtil.generateToken(username);
		res.addHeader("Authorization", "MeuVizinhoComeChurros|=wO'M*ZDH5+46/_YEwxCom12YearsAzuis" + token);
		res.addHeader("access-control-expose-headers", "Authorization");
	}

	private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {

		@Override
		public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
				AuthenticationException exception) throws IOException, ServletException {

			response.setContentType("application/json");
			if (exception instanceof NoPermissionsException) {
				response.setStatus(403);
				response.getWriter().append(json(exception.getMessage()));

			} else {
				response.setStatus(401);
				response.getWriter().append(json("Email or password is invalid"));
			}
		}

		private String json(String message) {
			long date = new Date().getTime();
			return "{\"timestamp\": " + date + ", " + "\"status\": 401, " + "\"error\": \"Not authorized\", "
					+ "\"message\": \"" + message + "\", " + "\"path\": \"/login\"}";
		}
	}
}
