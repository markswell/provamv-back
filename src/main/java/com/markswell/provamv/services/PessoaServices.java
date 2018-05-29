package com.markswell.provamv.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.markswell.provamv.model.Pessoa;
import com.markswell.provamv.model.Telefone;
import com.markswell.provamv.repository.PessoaRepositoy;
import com.markswell.provamv.repository.TelefoneRepository;

@Service 
public class PessoaServices {
	
	@Autowired
	private PessoaRepositoy rep;

	@Autowired
	private TelefoneRepository reTel;
	
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

	public List<Pessoa> findByCpf(String cpf) {
		return rep.getByCpf(cpf);
	}
	
	public void delete(long id) {
		Pessoa pessoa = rep.getOne(id);
		rep.delete(pessoa);
	}
	
	public Pessoa save(Pessoa pessoa) {
		List<Telefone> telefones = pessoa.getTelefones();
		pessoa.setTelefones(null);
		Pessoa save = rep.save(pessoa);
		telefones.forEach(p -> {
			p.setPessoa(save);
			reTel.save(p);
		});
		List<Telefone> retornoTelefones = save.getTelefones();
		save.setTelefones(retornoTelefones);
		return save;
		
	}

	public Pessoa atualizar(Long id,Pessoa pessoa) {
		Pessoa salvar = findById(id);
		BeanUtils.copyProperties(pessoa, salvar, "id");
		return save(salvar);
		
	}

}
