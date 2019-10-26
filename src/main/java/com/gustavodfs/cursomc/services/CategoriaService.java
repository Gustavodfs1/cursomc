package com.gustavodfs.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.gustavodfs.cursomc.domain.Categoria;
import com.gustavodfs.cursomc.repositories.CategoriaRepository;
import com.gustavodfs.cursomc.services.execptions.DataExceptionException;
import com.gustavodfs.cursomc.services.execptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	
		
	}
	
	
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		buscar(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		buscar(id);
		try {
		repo.deleteById(id);
	}catch (DataIntegrityViolationException e) {
		throw new DataExceptionException("Não é possivel excluir uma categoria que possui produtos");
	}
	}
	
	
	public List<Categoria> findAll(){
		return repo.findAll();
	}
	
	
	
}
