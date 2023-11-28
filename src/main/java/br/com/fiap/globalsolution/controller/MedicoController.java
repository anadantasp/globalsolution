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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.globalsolution.error.exceptions.ResourceNotFoundException;
import br.com.fiap.globalsolution.model.Pessoa.Medico;
import br.com.fiap.globalsolution.repository.MedicoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/medicos")
@Slf4j
public class MedicoController {

    @Autowired
    MedicoRepository medicoRepository;

    @GetMapping
    public List<Medico> index(){
        log.info("buscando todos os medicos");
        return medicoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> show(@PathVariable Long id){
        log.info("mostrar medico com id " + id);
        return ResponseEntity.ok(getMedicoById(id));
    }

    @GetMapping("/crm/{crm}")
    public ResponseEntity<Medico> show(@PathVariable String crm){
        log.info("mostrar medico " + crm);
        return ResponseEntity.ok(medicoRepository.findByCrm(crm));
    }

    @GetMapping("/login")
    public ResponseEntity<Medico> login(@RequestParam String email, @RequestParam String senha){
        log.info("login medico " + email);
        return ResponseEntity.ok(getMedicoByEmailAndSenha(email, senha));
    }



    @PostMapping
    public ResponseEntity<Medico> create(@RequestBody @Valid Medico medico){
        log.info("cadastrando medico: " + medico);
        medicoRepository.save(medico);
        return ResponseEntity.status(HttpStatus.CREATED).body(medico);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> update(@PathVariable Long id, @RequestBody Medico medico){
        log.info("atualizando dados do medico com id " + id);

        getMedicoById(id);

        medico.setId(id);
        medicoRepository.save(medico);

        return ResponseEntity.ok(medico);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Medico> destroy(@PathVariable Long id){
        log.info("apagando medico com id " + id);

        medicoRepository.delete(getMedicoById(id));

        return ResponseEntity.noContent().build();
    }

    private Medico getMedicoById(Long id){
        return medicoRepository.findById(id).orElseThrow(() -> { 
             return new ResourceNotFoundException("Entidade não encontrada");
         });
     }

     private Medico getMedicoByEmailAndSenha(String email, String senha){
        return medicoRepository.findByEmailAndSenha(email, senha).orElseThrow(() -> { 
             return new ResourceNotFoundException("Medico não encontrado");
         });
     }
}
