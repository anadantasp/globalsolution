package br.com.fiap.globalsolution.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
import br.com.fiap.globalsolution.model.ItemReceita;
import br.com.fiap.globalsolution.repository.ItemReceitaRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/itensreceitas")
@Slf4j
public class ItemReceitaController {

    @Autowired
    ItemReceitaRepository itemReceitaRepository;

    @GetMapping
    public Page<ItemReceita> index(
        @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageRequest){
        log.info("buscando todos os itens de receitas");
        return itemReceitaRepository.findAll(pageRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemReceita> show(@PathVariable Long id){
        log.info("mostrar item receita com id " + id);
        return ResponseEntity.ok(getItemReceitaById(id));
    }

    @PostMapping
    public ResponseEntity<ItemReceita> create(@RequestBody @Valid ItemReceita itemReceita){
        log.info("cadastrando item receita: " + itemReceita);
        itemReceitaRepository.save(itemReceita);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemReceita);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemReceita> update(@PathVariable Long id, @RequestBody @Valid ItemReceita itemReceita){
        log.info("atualizando dados de item receita com id " + id);

        getItemReceitaById(id);

        itemReceita.setId(id);
        itemReceitaRepository.save(itemReceita);

        return ResponseEntity.ok(itemReceita);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ItemReceita> destroy(@PathVariable Long id){
        log.info("apagando item receita com id " + id);

        itemReceitaRepository.delete(getItemReceitaById(id));

        return ResponseEntity.noContent().build();
    }

    private ItemReceita getItemReceitaById(Long id){
        return itemReceitaRepository.findById(id).orElseThrow(() -> { 
             return new ResourceNotFoundException("Entidade não encontrada");
         });
     }
    
}
