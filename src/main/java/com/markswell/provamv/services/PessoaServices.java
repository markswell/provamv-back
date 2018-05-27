package com.markswell.provamv.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.markswell.provamv.model.Pessoa;
import com.markswell.provamv.repository.PessoaRepositoy;

@Service 
public class PessoaServices {
	
	@Autowired
	private PessoaRepositoy rep;
	
	public List<Pessoa> findAll(){
		return rep.findAll();
	}
	
	public Pessoa findById(Long id) {
		Optional<Pessoa> findById = rep.findById(id);
		return findById.isPresent() ? findById.get() : null; 
	}
	
	public List<Pessoa> findByNome(String nome) {
		List<Pessoa> pessoa = rep.getByNome(nome);
		return pessoa; 
	}

	public Pessoa findByCpf(String cpf) {
		return rep.getByCpf(cpf);
	}
	
	public void delete(long id) {
		Pessoa pessoa = rep.getOne(id);
		rep.delete(pessoa);
	}
	
	public Pessoa save(Pessoa pessoa) {
		return rep.save(pessoa);
	}

	public Pessoa atualizar(Pessoa pessoa) {
		return rep.save(pessoa);
		
	}

}
