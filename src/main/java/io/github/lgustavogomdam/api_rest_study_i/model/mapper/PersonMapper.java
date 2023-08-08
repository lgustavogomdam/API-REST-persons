package io.github.lgustavogomdam.api_rest_study_i.model.mapper;

import io.github.lgustavogomdam.api_rest_study_i.model.dto.PersonDTO;
import io.github.lgustavogomdam.api_rest_study_i.model.entities.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/*
=========================================|Anotação @Mapper|=============================================================

Para usar o @Mapper no spring o aconselhável é definir o atributo componentModel = 'spring' pois dessa forma, o spring
irá reconhecer como um bean e poderemos utilizar da injeção de dependências dele ao chamarmos o atributo-interface na
classe que o possua.

=========================================|Anotação @Mapping|=============================================================

O @Mapping serve para passarmos informações ao mapear objetos, como por exemplo se os objetos tiverem atributos com nome
distintos, é por essa anotação que iremos identificá-los, o source = '<atributoOriginal>' é referente a conversão do
objeto passado como parâmetro e o target = '<atributoAlvo>' é referente ao atributo do objeto-parâmetro.

=====================|Mappers.getMapper(PersonMapper.class)

Serve para capturar uma instancia da classe especificada.

=====================|Infos

O MapStruct é um gerador de código para facilitar a conversão entre tipos, o código gerado por ele fica em:

"target>generated-sources>annotations>"Seu_Diretorio">"Sua_Classe_Gerada"
*/

@Mapper(componentModel = "spring")
public interface PersonMapper{
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    //Entidade -> ModelDTO

    PersonDTO toModel(PersonEntity entity);
    List<PersonDTO> toModelList(List<PersonEntity> entity);

    //ModelDTO -> Entidade
    PersonEntity toEntity(PersonDTO dto);
    List<PersonEntity> toEntityList(List<PersonDTO> modelDTO);
}
