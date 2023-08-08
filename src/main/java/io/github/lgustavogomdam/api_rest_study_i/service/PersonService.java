package io.github.lgustavogomdam.api_rest_study_i.service;

import io.github.lgustavogomdam.api_rest_study_i.model.dto.PersonDTO;
import io.github.lgustavogomdam.api_rest_study_i.model.entities.PersonEntity;
import io.github.lgustavogomdam.api_rest_study_i.model.mapper.PersonMapper;
import io.github.lgustavogomdam.api_rest_study_i.repository.PersonRepository;
import io.github.lgustavogomdam.api_rest_study_i.service.exceptions.ResourceNotFoundException;
import jakarta.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService{
    private Logger logger = Logger.getLogger(PersonService.class.getName());
    @Autowired
    private PersonRepository repository;

    @Autowired
    private PersonMapper mapper;

    public List<PersonDTO> findAll(){

        logger.info("Finding all people!");

        List<PersonEntity> listOfPersonsEntity =  this.repository.findAll();

        if(listOfPersonsEntity.isEmpty())
            return null;

        return mapper.toModelList(listOfPersonsEntity);
    }
    public PersonDTO findByID(Long id){

        logger.info("Finding a personEntity!");

        PersonDTO model = mapper.toModel(this.repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Elemento não encontrado!")));

        if (model != null)
            return model;
        else
            return null;
    }

    public PersonDTO create(PersonDTO personDTO){

        logger.info("Create a personEntity!");

        PersonEntity personEntity = (PersonEntity) mapper.toEntity(personDTO);

        return (PersonDTO) mapper.toModel((this.repository.save(personEntity)));
    }

    public PersonDTO update(PersonDTO person){

        logger.info("Update a personEntity!");

        PersonEntity personEntity = mapper.toEntity(person);
        PersonEntity entity = this.repository.findById(person.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Elemento não encontrado!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return mapper.toModel((this.repository.save(entity)));
    }

    public void delete(Long id) {

        logger.info("Delete a person!");
        PersonEntity person = this.repository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Elemento não encontrado!"));

        this.repository.delete(person);
    }
}
