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
import br.com.fiap.globalsolution.model.Especialidade;
import br.com.fiap.globalsolution.repository.EspecialidadeRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/especialidades")
@Slf4j
public class EspecialidadeController {

    @Autowired
    EspecialidadeRepository especialidadeRepository;

    @GetMapping
    public List<Especialidade> index(){
        log.info("buscando todos as especialidades");
        return especialidadeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Especialidade> show(@PathVariable Long id){
        log.info("mostrar especialidade com id " + id);
        return ResponseEntity.ok(getEspecialidadeById(id));
    }

    @PostMapping
    public ResponseEntity<Especialidade> create(@RequestBody @Valid Especialidade especialidade){
        log.info("cadastrando especialidade: " + especialidade);
        especialidadeRepository.save(especialidade);
        return ResponseEntity.status(HttpStatus.CREATED).body(especialidade);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Especialidade> update(@PathVariable Long id, @RequestBody @Valid Especialidade especialidade){
        log.info("atualizando dados do especialidade com id " + id);

        getEspecialidadeById(id);

        especialidade.setId(id);
        especialidadeRepository.save(especialidade);

        return ResponseEntity.ok(especialidade);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Especialidade> destroy(@PathVariable Long id){
        log.info("apagando especialidade com id " + id);

        especialidadeRepository.delete(getEspecialidadeById(id));

        return ResponseEntity.noContent().build();
    }

    private Especialidade getEspecialidadeById(Long id){
        return especialidadeRepository.findById(id).orElseThrow(() -> { 
             return new ResourceNotFoundException("Entidade não encontrada");
         });
     }
}
