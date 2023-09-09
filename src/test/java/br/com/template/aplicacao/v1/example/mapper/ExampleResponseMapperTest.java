package br.com.template.aplicacao.v1.example.mapper;

import br.com.template.aplicacao.v1.example.fixture.ExampleFixture;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class ExampleResponseMapperTest {

    @Test
    void testConverterExampleEntityToExampleResponseWithSuccess() {

        var expected = ExampleFixture.getExampleResponse();

        var exampleEntity = ExampleFixture.getExampleEntity();

        var actual = ExampleResponseMapper.INSTANCE.mapEntityToResponse(exampleEntity);

        Assert.assertEquals(expected, actual);
    }

    @Test
    void testConverterExampleEntityToExampleResponseWithFailed() {

        var expectedExampleResponse = ExampleFixture.getExampleResponse();
        expectedExampleResponse.setId(2L);

        var exampleEntity = ExampleFixture.getExampleEntity();

        var actualExampleResponse = ExampleResponseMapper.INSTANCE.mapEntityToResponse(exampleEntity);

        Assert.assertNotEquals(expectedExampleResponse, actualExampleResponse);
    }
}
