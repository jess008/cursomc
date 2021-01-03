package com.menezes.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.menezes.cursomc.domain.Cliente;
import com.menezes.cursomc.repositories.ClienteRepository;
import com.menezes.cursomc.services.exceptions.ObjNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {     
		Optional<Cliente> obj = repo.findById(id); 
		return obj.orElseThrow(() -> new ObjNotFoundException( "Objeto não encontrado! Id: " + id 
							+ ", Tipo: " + Cliente.class.getName()));  
	}


}
