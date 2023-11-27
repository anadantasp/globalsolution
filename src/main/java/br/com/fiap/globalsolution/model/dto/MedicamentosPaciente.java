package br.com.fiap.globalsolution.model.dto;

import java.util.Set;

import br.com.fiap.globalsolution.model.ItemReceita;

public record MedicamentosPaciente(String cpf, String nome, Set<ItemReceita> medicamentos) {
    
}
