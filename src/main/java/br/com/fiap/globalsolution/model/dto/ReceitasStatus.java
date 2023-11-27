package br.com.fiap.globalsolution.model.dto;

import java.util.List;

import br.com.fiap.globalsolution.model.Receita;

public record ReceitasStatus(boolean status, List<Receita> receitas) {
    
}
