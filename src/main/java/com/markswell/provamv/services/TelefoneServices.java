package com.markswell.provamv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.markswell.provamv.model.Telefone;
import com.markswell.provamv.repository.TelefoneRepository;

@Service
public class TelefoneServices {
	
	@Autowired
	private TelefoneRepository repository;
	
	public void deletar( Long id){
		Telefone telefone = repository.getOne(id);
		repository.delete(telefone);
	}
	
}
