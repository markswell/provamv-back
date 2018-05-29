package com.markswell.provamv.controller;

import java.net.URI;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	
	@CrossOrigin(origins= {"http://localhost:4200"})
	@GetMapping("/pessoas")
	public ResponseEntity<List<Pessoa>> getPessoas(){
		List<Pessoa> retorno = services.findAll();
		return !retorno.isEmpty() ? ResponseEntity.ok(retorno) : ResponseEntity.noContent().build();
	} 
	@CrossOrigin(origins= {"http://localhost:4200"})
	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> getPessoa(@PathVariable Long id){
		Pessoa pessoa = services.findById(id);
		return pessoa != null ? ResponseEntity.ok(pessoa) : ResponseEntity.noContent().build() ;
	}
	@CrossOrigin(origins= {"http://localhost:4200"})
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Pessoa>> getPessoaByNome(@PathVariable String nome){
		List<Pessoa> pessoas = services.findByNome(nome);
		return pessoas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(pessoas);
	}
	@CrossOrigin(origins= {"http://localhost:4200"})
	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<List<Pessoa>> getPessoaByCpf(@PathVariable String cpf){
		List<Pessoa> pessoa = services.findByCpf(cpf);
		return pessoa != null ? ResponseEntity.ok(pessoa) : ResponseEntity.noContent().build() ;
	}
	@CrossOrigin(origins= {"http://localhost:4200"})
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
	@CrossOrigin(origins= {"http://localhost:4200"})
	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> atualizar(@PathVariable Long id, @RequestBody Pessoa pessoa){
		try {
			Pessoa retorno = services.atualizar(id, pessoa);
			return ResponseEntity.ok(retorno);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	@CrossOrigin(origins= {"http://localhost:4200"})
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id){
		services.delete(id);
	}

}
