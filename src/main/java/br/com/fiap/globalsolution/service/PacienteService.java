package br.com.fiap.globalsolution.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.globalsolution.model.Receita;
import br.com.fiap.globalsolution.model.dto.MedicamentosPaciente;
import br.com.fiap.globalsolution.model.dto.ReceitasStatus;
import br.com.fiap.globalsolution.repository.ReceitaRepository;

@Service
public class PacienteService {

    @Autowired
    ReceitaRepository receitaRepository;

    public List<MedicamentosPaciente> getMedicamentosPaciente(String cpf){

        var receitas = receitaRepository.findAll();

        var medicamentos = receitas.stream()
                    .filter(receita -> receita.getPaciente().getCpf().equals(cpf))
                    .map(e -> new MedicamentosPaciente(e.getPaciente().getCpf(), e.getPaciente().getNome(), e.getMedicamentos()))
                    .collect(Collectors.toList());
        
        return medicamentos;

        

    }

    public List<Receita> getReceitaPaciente(String cpf) {
        var receitas = receitaRepository.findAll();
        var receitasPaciente = receitas.stream()
                    .filter(receita -> receita.getPaciente().getCpf().equals(cpf))
                    .collect(Collectors.toList());

        return receitasPaciente;


    }

    public List<ReceitasStatus> getReceitaPacientePorStatus(String cpf) {
        var receitas = receitaRepository.findAll();
        var receitasPaciente = receitas.stream()
                    .filter(receita -> receita.getPaciente().getCpf().equals(cpf))
                    .collect(Collectors.groupingBy(Receita::isStatus))
                    .entrySet()
                    .stream()
                    .map(e -> new ReceitasStatus(e.getKey(), e.getValue()))
                    .collect(Collectors.toList());

        return receitasPaciente;


    }
    
}
