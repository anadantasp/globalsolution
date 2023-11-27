package br.com.fiap.globalsolution.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.globalsolution.model.ItemReceita;

public interface ItemReceitaRepository extends JpaRepository<ItemReceita, Long>{
    
}
