package com.pethouse.aula.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pethouse.aula.domain.Pedido;
import com.pethouse.aula.exceptions.ObjectNotFoundException;
import com.pethouse.aula.repositories.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	public Pedido find(Integer id) {
		Optional <Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException (
				"Objeto não encontrado! Id: "+ id + ", Tipo: " + Pedido.class.getName()) );
	}

}
