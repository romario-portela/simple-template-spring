package br.com.template.aplicacao.v1.example.mapper;

import br.com.template.aplicacao.v1.example.domain.ExampleEntity;
import br.com.template.aplicacao.v1.example.model.response.ExampleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExampleResponseMapper {
    ExampleResponseMapper INSTANCE = Mappers.getMapper(ExampleResponseMapper.class);

    ExampleResponse mapEntityToResponse(ExampleEntity entity);
}
