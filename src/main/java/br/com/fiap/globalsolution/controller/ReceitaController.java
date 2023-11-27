package br.com.fiap.globalsolution.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.globalsolution.error.exceptions.ResourceNotFoundException;
import br.com.fiap.globalsolution.model.Receita;
import br.com.fiap.globalsolution.model.dto.ReceitasStatus;
import br.com.fiap.globalsolution.repository.ReceitaRepository;
import br.com.fiap.globalsolution.service.PacienteService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/receitas")
@Slf4j
public class ReceitaController {

    @Autowired
    ReceitaRepository receitaRepository;

    @Autowired
    PacienteService pacienteService;

    @GetMapping
    public List<Receita> index(){
        log.info("buscando todos as receitas");
        return receitaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receita> show(@PathVariable Long id){
        log.info("mostrar receita com id " + id);
        return ResponseEntity.ok(getReceitaById(id));
    }

    @GetMapping("/paciente/{cpf}")
    public ResponseEntity<List<Receita>> getReceitaPorCPF(@PathVariable String cpf){
        log.info("mostrar receitas do paciente " + cpf);
        return ResponseEntity.ok(pacienteService.getReceitaPaciente(cpf));
        
    }

    @GetMapping("/status/{cpf}")
    public ResponseEntity<List<ReceitasStatus>> getReceitaPorStatusPorPaciente(@PathVariable String cpf){
        log.info("mostrar receitas por status");
        return ResponseEntity.ok(pacienteService.getReceitaPacientePorStatus(cpf));
        
    }

    @PostMapping
    public ResponseEntity<Receita> create(@RequestBody @Valid Receita receita){
        log.info("cadastrando receita: " + receita);
        receitaRepository.save(receita);
        return ResponseEntity.status(HttpStatus.CREATED).body(receita);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Receita> update(@PathVariable Long id, @RequestBody @Valid Receita receita){
        log.info("atualizando dados do receita com id " + id);

        getReceitaById(id);

        receita.setId(id);
        receitaRepository.save(receita);

        return ResponseEntity.ok(receita);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Receita> destroy(@PathVariable Long id){
        log.info("apagando receita com id " + id);

        receitaRepository.delete(getReceitaById(id));

        return ResponseEntity.noContent().build();
    }

    private Receita getReceitaById(Long id){
        return receitaRepository.findById(id).orElseThrow(() -> { 
             return new ResourceNotFoundException("Entidade não encontrada");
         });
     }
    
}
