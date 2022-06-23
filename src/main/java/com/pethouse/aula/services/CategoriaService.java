package com.pethouse.aula.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pethouse.aula.domain.Categoria;
import com.pethouse.aula.exceptions.ObjectNotFoundException;
import com.pethouse.aula.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	public Categoria find(Integer id) {
		Optional <Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException (
				"Objeto não encontrado! Id: "+ id + ", Tipo: " + Categoria.class.getName()) );
	}

}
