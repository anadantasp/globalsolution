package br.com.fiap.globalsolution.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.globalsolution.model.Pessoa.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    
}
