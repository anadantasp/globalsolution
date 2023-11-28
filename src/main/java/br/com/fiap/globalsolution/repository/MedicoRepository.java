package br.com.fiap.globalsolution.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.globalsolution.model.Pessoa.Medico;


public interface MedicoRepository extends JpaRepository<Medico, Long>{

    public Medico findByCrm(String cpf);

    public Optional<Medico> findByEmailAndSenha(String email, String senha);
    
}
