package com.test.order;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.test.order.dto.ThreadLocalObject;
import com.test.order.listners.AuditListener;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		final String authorizationHeader = request.getHeader("Authorization");
		String username = null;
		String jwt = null;
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			jwt = authorizationHeader.substring(7);
			ThreadLocalObject obj = new ThreadLocalObject();
			obj.setJsonTocken(jwt);
			username = jwtUtil.extractUsername(jwt);

			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

				UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

				if (jwtUtil.validateToken(jwt, userDetails)) {
					AuditListener.setAuditUserDetail(obj);
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					usernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					chain.doFilter(request, response);
				} else {
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Forbidden Request");
				}
			}
		} else {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Forbidden Request");
		}

		AuditListener.clearThreadLocal();
	}

}
