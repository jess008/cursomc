package com.menezes.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.menezes.cursomc.domain.Categoria;
import com.menezes.cursomc.repositories.CategoriaRepository;
import com.menezes.cursomc.services.exceptions.ObjNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {     
		Optional<Categoria> obj = repo.findById(id); 
		return obj.orElseThrow(() -> new ObjNotFoundException( "Objeto não encontrado! Id: " + id 
							+ ", Tipo: " + Categoria.class.getName()));  
	}


}
