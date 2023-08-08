package io.github.lgustavogomdam.api_rest_study_i.controller;

import io.github.lgustavogomdam.api_rest_study_i.model.dto.PersonDTO;
import io.github.lgustavogomdam.api_rest_study_i.model.entities.PersonEntity;
import io.github.lgustavogomdam.api_rest_study_i.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 Requisição -> Controller (Controller valida requisição)
 Controller -> Service (Service é a lógica da aplicação e regras de negócio)
 Service -> Repository (Repository é o DAO, Objetos de acesso a dados - conexão com banco de dados)
 Repository -> Model (Model é o mapeamento das entidades no banco de dados)
*/
@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonDTO> findAll(){

        return personService.findAll();
    }
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDTO findByID(@PathVariable(value = "id") Long id){

        return personService.findByID(id);
    }
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonDTO create(@RequestBody PersonDTO person){

        return personService.create(person);
    }
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonDTO update(@RequestBody PersonDTO person){

        return personService.update(person);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Long id){

        personService.delete(id);

        return new ResponseEntity<>("Elemento deletado com sucesso", HttpStatus.NO_CONTENT);
    }


}
