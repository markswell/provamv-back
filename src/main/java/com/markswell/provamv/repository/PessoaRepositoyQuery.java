package com.markswell.provamv.repository;

import java.util.List;

import com.markswell.provamv.model.Pessoa;

public interface PessoaRepositoyQuery {
	
	public List<Pessoa> getByNome(String nome);
	public List<Pessoa> getByCpf(String nome);

}
