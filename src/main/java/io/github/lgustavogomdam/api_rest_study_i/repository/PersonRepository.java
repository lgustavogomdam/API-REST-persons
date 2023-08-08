package io.github.lgustavogomdam.api_rest_study_i.repository;

import io.github.lgustavogomdam.api_rest_study_i.model.dto.PersonDTO;
import io.github.lgustavogomdam.api_rest_study_i.model.entities.PersonEntity;

import io.github.lgustavogomdam.api_rest_study_i.model.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity,Long> {

    void deleteById(Long id);

    public PersonEntity save(PersonEntity entity);

}
