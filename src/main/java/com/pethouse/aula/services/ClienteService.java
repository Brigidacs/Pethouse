package com.pethouse.aula.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pethouse.aula.domain.Cliente;
import com.pethouse.aula.exceptions.ObjectNotFoundException;
import com.pethouse.aula.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	public Cliente find(Integer id) {
		Optional <Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException (
				"Objeto não encontrado! Id: "+ id + ", Tipo: " + Cliente.class.getName()) );
	}

}
