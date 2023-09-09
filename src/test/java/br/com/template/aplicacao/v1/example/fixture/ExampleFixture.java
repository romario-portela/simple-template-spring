package br.com.template.aplicacao.v1.example.fixture;

import br.com.template.aplicacao.domain.ValidationResultDto;
import br.com.template.aplicacao.v1.example.domain.DiscountTypeEnum;
import br.com.template.aplicacao.v1.example.domain.ExampleEntity;
import br.com.template.aplicacao.v1.example.model.request.ExampleRequest;
import br.com.template.aplicacao.v1.example.model.response.ExampleResponse;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@UtilityClass
public class ExampleFixture {
    public ExampleRequest getExampleRequest() {
        return ExampleRequest.builder()
                .startDate(LocalDateTime.of(2020, 1, 1, 1, 1))
                .active(Boolean.TRUE)
                .price(BigDecimal.valueOf(100L))
                .limitUses(1)
                .description("Descrição")
                .discountType(DiscountTypeEnum.PRICE)
                .build();
    }

    public ExampleEntity getExampleEntity() {
        return ExampleEntity.builder()
                .startDate(LocalDateTime.of(2020, 1, 1, 1, 1))
                .active(Boolean.TRUE)
                .price(BigDecimal.valueOf(100L))
                .limitUses(1)
                .description("Descrição")
                .discountType(DiscountTypeEnum.PRICE.getValue())
                .build();
    }

    public ExampleResponse getExampleResponse() {
        return ExampleResponse.builder()
                .startDate(LocalDateTime.of(2020, 1, 1, 1, 1))
                .active(Boolean.TRUE)
                .price(BigDecimal.valueOf(100L))
                .limitUses(1)
                .description("Descrição")
                .discountType(DiscountTypeEnum.PRICE.getValue())
                .build();
    }

    public ExampleEntity getExampleEntityUpdate() {
        var exampleEntity = getExampleEntity();
        exampleEntity.setId(1L);
        return exampleEntity;
    }

    public ExampleEntity getExampleEntityWithPriceNull() {
        var exampleEntity = getExampleEntity();
        exampleEntity.setPrice(null);

        return exampleEntity;
    }

    public ExampleEntity getExampleEntityWithDiscTypeFixedPriceWithPriceNull() {
        var exampleEntity = getExampleEntity();
        exampleEntity.setDiscountType(DiscountTypeEnum.PRICE.getValue());
        exampleEntity.setPrice(null);

        return exampleEntity;
    }

    public ExampleEntity getExampleEntityWithDiscTypeMessageWithPriceNonNull() {
        var exampleEntity = getExampleEntity();
        exampleEntity.setDiscountType(DiscountTypeEnum.MESSAGE.getValue());
        exampleEntity.setPrice(BigDecimal.ONE);

        return exampleEntity;
    }

    public ExampleEntity getExampleEntityInactivated() {
        var exampleEntity = getExampleEntity();
        exampleEntity.setActive(Boolean.FALSE);
        return exampleEntity;
    }

    public ExampleRequest getExampleRequestSuccessWithDiscountTypeMessage() {
        var exampleRequest = getExampleRequest();
        exampleRequest.setDiscountType(DiscountTypeEnum.PRICE);
        return exampleRequest;
    }

    public ValidationResultDto getValidationResultDtoSucess() {
        return ValidationResultDto.builder()
                .valid(true)
                .build();
    }
}
