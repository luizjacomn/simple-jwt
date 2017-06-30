package com.github.jjljrj.controller;

import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.jjljrj.model.Cliente;
import com.github.jjljrj.security.KeyUtil;
import com.github.jjljrj.security.TokenUtil;
import com.github.jjljrj.service.ClienteService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class LoginController {

	private static final Date EXPIRATION = new Date(System.currentTimeMillis() + 2 * 60 * 1000);

	@Autowired
	private ClienteService service;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public TokenUtil autenticar(@RequestBody Cliente cliente) throws ServletException {

		if (cliente.getNome() == null || cliente.getSenha() == null)
			throw new ServletException("Nome e senha são obrigatórios!");

		Cliente clienteAutenticado = service.getClienteAutenticado(cliente);

		if (clienteAutenticado == null) {
			throw new ServletException("Cliente não encontrado!");
		}

		if (!clienteAutenticado.getSenha().equals(cliente.getSenha())) {
			throw new ServletException("Nome ou senha inválidos!");
		}

		String token = Jwts.builder().setSubject(cliente.getNome()).signWith(SignatureAlgorithm.HS512, KeyUtil.KEY)
				.setExpiration(EXPIRATION).compact();

		return new TokenUtil(token);
	}
}