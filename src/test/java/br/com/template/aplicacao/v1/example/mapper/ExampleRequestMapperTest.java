package br.com.template.aplicacao.v1.example.mapper;

import br.com.template.aplicacao.v1.example.fixture.ExampleFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ExampleRequestMapperTest {

    @Test
    void testConverterExampleRequestToExampleEntityWithSuccess() {
        // Arrange
        var exampleRequest = ExampleFixture.getExampleRequest();
        var expectedExampleEntity = ExampleFixture.getExampleEntity();

        // ACT
        var actualExampleEntity = ExampleRequestMapper.INSTANCE.mapModelToEntity(exampleRequest);


        // Assert
        Assertions.assertEquals(expectedExampleEntity, actualExampleEntity);







    }

    @Test
    void testConverterExampleRequestToExampleEntityWithFailed() {
        var exampleRequest = ExampleFixture.getExampleRequest();

        var expectedExampleEntity = ExampleFixture.getExampleEntity();
        expectedExampleEntity.setActive(false);

        var actualExampleEntity = ExampleRequestMapper.INSTANCE.mapModelToEntity(exampleRequest);

        Assertions.assertNotEquals(expectedExampleEntity, actualExampleEntity);
    }
}
