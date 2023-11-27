package br.com.fiap.globalsolution.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.globalsolution.model.Especialidade;

public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long>{
    
}
