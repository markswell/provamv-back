package com.markswell.provamv.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.markswell.provamv.model.Pessoa;

public interface PessoaRepositoy extends JpaRepository<Pessoa, Long>, PessoaRepositoyQuery{
	
	
}
