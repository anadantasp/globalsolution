package br.com.fiap.globalsolution.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.globalsolution.model.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Long>{
    
}
