package com.github.jjljrj.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.jjljrj.model.Cliente;
import com.github.jjljrj.service.ClienteService;

@RestController
@RequestMapping("/authenticated/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService service;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Cliente>> listAll() {
		return new ResponseEntity<>(service.getClientes(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente) {
		return new ResponseEntity<Cliente>(service.salvar(cliente), HttpStatus.CREATED);
	}
}