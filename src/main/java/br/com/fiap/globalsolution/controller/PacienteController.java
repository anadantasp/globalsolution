package br.com.fiap.globalsolution.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.globalsolution.error.exceptions.ResourceNotFoundException;
import br.com.fiap.globalsolution.model.Pessoa.Paciente;
import br.com.fiap.globalsolution.repository.PacienteRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/pacientes")
@Slf4j
public class PacienteController {

    @Autowired
    PacienteRepository pacienteRepository;

    @GetMapping
    public List<Paciente> index(){
        log.info("buscando todos os pacientes");
        return pacienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> show(@PathVariable Long id){
        log.info("mostrar paciente com id " + id);
        return ResponseEntity.ok(getPacienteById(id));
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Paciente> show(@PathVariable String cpf){
        log.info("mostrar paciente " + cpf);
        return ResponseEntity.ok(pacienteRepository.findByCpf(cpf));
    }

    private Paciente getPacienteById(Long id){
        return pacienteRepository.findById(id).orElseThrow(() -> { 
             return new ResourceNotFoundException("Entidade não encontrada");
         });
     }

    
}
