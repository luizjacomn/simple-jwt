package com.github.jjljrj.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;

import org.springframework.stereotype.Service;

import com.github.jjljrj.model.Cliente;

@Service
public class ClienteService {
	static Set<Cliente> db;

	static {
		db = new HashSet<>();
		db.add(new Cliente("teste", "123"));
	}

	public Cliente salvar(Cliente cliente) {
		db.add(cliente);
		return cliente;
	}

	public Collection<Cliente> getClientes() {
		return db;
	}

	public Cliente getClienteAutenticado(Cliente cliente) throws ServletException {
		if (getClientes().contains(cliente))
			return cliente;
		return null;
	}

}