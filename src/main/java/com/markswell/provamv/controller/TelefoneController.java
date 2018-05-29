package com.markswell.provamv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.markswell.provamv.services.TelefoneServices;

@RestController
@RequestMapping("/telefone")
public class TelefoneController {
	
	@Autowired
	private TelefoneServices services;
	
	@CrossOrigin(origins= {"http://localhost:4200"})
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id){
		services.deletar(id);
	}
}
