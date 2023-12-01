package br.com.fiap.globalsolution.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.globalsolution.model.Pessoa.Paciente;


public interface PacienteRepository extends JpaRepository<Paciente, Long>{

   Optional<Paciente> findByCpf(String cpf);
    
}
