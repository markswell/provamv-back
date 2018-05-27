package com.markswell.provamv.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.markswell.provamv.model.Pessoa;
import com.markswell.provamv.services.PessoaServices;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
	
	@Autowired
	private PessoaServices services;
	
	@GetMapping("/teste")
	public ResponseEntity<String> teste(){
		return ResponseEntity.ok("Bem vindo a API rest da provamv.");
	}
	
	@GetMapping("/pessoas")
	public ResponseEntity<List<Pessoa>> getPessoas(){
		List<Pessoa> retorno = services.findAll();
		return !retorno.isEmpty() ? ResponseEntity.ok(retorno) : ResponseEntity.noContent().build();
	} 
	
	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> getPessoa(@PathVariable Long id){
		Pessoa pessoa = services.findById(id);
		return pessoa != null ? ResponseEntity.ok(pessoa) : ResponseEntity.noContent().build() ;
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Pessoa>> getPessoaByNome(@PathVariable String nome){
		List<Pessoa> pessoas = services.findByNome(nome);
		return pessoas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(pessoas);
//		return !pessoas.isEmpty() ? ResponseEntity.ok(pessoas) : ResponseEntity.noContent().build() ;
	}

	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<Pessoa> getPessoaByCpf(@PathVariable String cpf){
		Pessoa pessoa = services.findByCpf(cpf);
		return pessoa != null ? ResponseEntity.ok(pessoa) : ResponseEntity.noContent().build() ;
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> inserir(@RequestBody Pessoa pessoa,  HttpServletResponse response) {
		try {
			Pessoa pessoaSalva = services.save(pessoa);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
			.path("/{id}").buildAndExpand(pessoaSalva.getId()).toUri();
			response.setHeader("Location", uri.toASCIIString());
			return ResponseEntity.created(uri).body(pessoaSalva);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> atualizar(@PathVariable Long id, @RequestBody Pessoa pessoa){
		try {
			Pessoa salvar = services.findById(id);
			BeanUtils.copyProperties(pessoa, salvar, "id");
			Pessoa retorno = services.atualizar(salvar);
			
			return ResponseEntity.ok(retorno);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
