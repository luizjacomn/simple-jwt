package com.github.jjljrj.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

import com.github.jjljrj.security.KeyUtil;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

public class AuthenticationFilter extends GenericFilterBean {

	private static final String TOKEN_INVALIDO_ERROR = "Token inválido!";
	private static final String TOKEN_INEXISTENTE_ERROR = "Token inexistente!";
	private static final String AUTHORIZATION = "Authorization";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		String auth = req.getHeader(AUTHORIZATION);

		try {
			if (auth == null || auth.contains("null"))
				throw new ServletException(TOKEN_INEXISTENTE_ERROR);

			String token = auth.substring(7);

			Jwts.parser().setSigningKey(KeyUtil.KEY).parseClaimsJws(token).getBody();
		} catch (SignatureException e) {
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, TOKEN_INVALIDO_ERROR);
		} catch (ServletException e) {
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
		}

		chain.doFilter(request, response);
	}

}
