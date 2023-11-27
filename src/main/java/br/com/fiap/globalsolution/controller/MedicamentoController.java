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
import br.com.fiap.globalsolution.model.Medicamento;
import br.com.fiap.globalsolution.model.dto.MedicamentosPaciente;
import br.com.fiap.globalsolution.repository.MedicamentoRepository;
import br.com.fiap.globalsolution.service.PacienteService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/medicamentos")
@Slf4j
public class MedicamentoController {
    
    @Autowired
    MedicamentoRepository medicamentoRepository;

    @Autowired
    PacienteService pacienteService;

    @GetMapping
    public List<Medicamento> index(){
        log.info("buscando todos os medicamentos");
        return medicamentoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicamento> show(@PathVariable Long id){
        log.info("mostrar medicamento com id " + id);
        return ResponseEntity.ok(getMedicamentoById(id));
    }

    @GetMapping("/paciente/{cpf}")
    public ResponseEntity<List<MedicamentosPaciente>> getMedicamentosPorCPF(@PathVariable String cpf){
        log.info("mostrar medicamentos do paciente " + cpf);
        return ResponseEntity.ok(pacienteService.getMedicamentosPaciente(cpf));
        
    }

    @PostMapping
    public ResponseEntity<Medicamento> create(@RequestBody @Valid Medicamento medicamento){
        log.info("cadastrando medicamento: " + medicamento);
        medicamentoRepository.save(medicamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicamento);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Medicamento> update(@PathVariable Long id, @RequestBody @Valid Medicamento medicamento){
        log.info("atualizando dados do medicamento com id " + id);

        getMedicamentoById(id);

        medicamento.setId(id);
        medicamentoRepository.save(medicamento);

        return ResponseEntity.ok(medicamento);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Medicamento> destroy(@PathVariable Long id){
        log.info("apagando medicamento com id " + id);

        medicamentoRepository.delete(getMedicamentoById(id));

        return ResponseEntity.noContent().build();
    }

    private Medicamento getMedicamentoById(Long id){
        return medicamentoRepository.findById(id).orElseThrow(() -> { 
             return new ResourceNotFoundException("Entidade não encontrada");
         });
     }
}
