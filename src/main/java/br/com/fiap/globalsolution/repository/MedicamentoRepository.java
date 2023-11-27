package br.com.fiap.globalsolution.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.globalsolution.model.Medicamento;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long>{
    
}
