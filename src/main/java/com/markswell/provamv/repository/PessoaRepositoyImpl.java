package com.markswell.provamv.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.markswell.provamv.model.Pessoa;

public class PessoaRepositoyImpl implements PessoaRepositoyQuery {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Pessoa> getByNome(String nome) {
		Query query = manager.createQuery("select p from Pessoa p where p.nome like :arg").setParameter("arg", "%" + nome + "%");
		return query.getResultList();
	}

	@Override
	public Pessoa getByCpf(String cpf) {
		Query query = manager.createQuery("select p from Pessoa p where p.cpf like :arg", Pessoa.class).setParameter("arg", cpf);
		return (Pessoa) query.getSingleResult();
	}

}
