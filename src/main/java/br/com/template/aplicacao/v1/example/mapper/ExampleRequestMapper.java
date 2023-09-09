package br.com.template.aplicacao.v1.example.mapper;

import br.com.template.aplicacao.v1.example.domain.ExampleEntity;
import br.com.template.aplicacao.v1.example.model.request.ExampleRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExampleRequestMapper {
    ExampleRequestMapper INSTANCE = Mappers.getMapper(ExampleRequestMapper.class);

    @Mapping(target = "discountType", expression = "java(request.getDiscountType() == null ? null : request.getDiscountType().getValue())")
    @Mapping(target = "id", ignore = true)
    ExampleEntity mapModelToEntity(ExampleRequest request);
}
