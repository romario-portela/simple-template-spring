package br.com.template.aplicacao.v1.example;

import br.com.template.aplicacao.v1.example.fixture.ExampleFixture;
import br.com.template.aplicacao.v1.example.mapper.ExampleResponseMapper;
import br.com.template.aplicacao.v1.example.model.request.ExampleRequest;
import br.com.template.aplicacao.v1.example.provider.ExampleRequestSuccessArgumentProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
@SpringBootTest
class ExampleControllerTest {

    @InjectMocks
    private ExampleController exampleController;

    @Mock
    private ExampleFacade exampleFacade;

    @ParameterizedTest
    @ValueSource(longs = {1L})
    void getExampleByIdShouldReturnOkWithExampleDataResponse(long id) {
        given(exampleFacade.findById(id)).willReturn(ExampleFixture.getExampleResponse());

        var expectedExampleResponse = ExampleFixture.getExampleResponse();
        var exampleResponse = exampleController.getExample(id);

        Assertions.assertEquals(expectedExampleResponse, exampleResponse);
    }

    @ParameterizedTest
    @ArgumentsSource(ExampleRequestSuccessArgumentProvider.class)
    void shouldCreateExampleEntityTest(ExampleRequest exampleRequest) {
        var expectedExampleEntity = ExampleFixture.getExampleResponse();

        given(exampleFacade.insert(exampleRequest)).willReturn(ExampleFixture.getExampleResponse());

        var exampleResponse = exampleController.createExample(exampleRequest);

        Assertions.assertEquals(exampleResponse, expectedExampleEntity);
    }

    @ParameterizedTest
    @ArgumentsSource(ExampleRequestSuccessArgumentProvider.class)
    void shouldUpdateExample(ExampleRequest exampleRequest) {
        var expectedExampleResponse = ExampleFixture.getExampleResponse();

        given(exampleFacade.findById(1L)).willReturn(ExampleFixture.getExampleResponse());
        given(exampleFacade.update(1L, exampleRequest)).willReturn(ExampleFixture.getExampleResponse());

        var exampleResponse = exampleController.updateExample(1L, exampleRequest);

        Assertions.assertEquals(expectedExampleResponse, exampleResponse);
    }

    @ParameterizedTest
    @ValueSource(longs = {1L})
    void shouldInactivateExample(long id) {
        given(exampleFacade.findById(id)).willReturn(ExampleFixture.getExampleResponse());

        exampleController.inactivateExample(id);

        then(exampleFacade).should(times(1)).inactivateExample(id);
    }

    @ParameterizedTest
    @ValueSource(longs = {1L})
    void shouldActivateExample(long id) {
        given(exampleFacade.findById(id)).willReturn(ExampleFixture.getExampleResponse());

        exampleController.activateExample(id);

        then(exampleFacade).should(times(1)).activateExample(id);
    }

    @ParameterizedTest
    @ValueSource(longs = {1L})
    void getListAllExampleResponseTest(long id) {
        var exampleEntity = ExampleFixture.getExampleEntity();

        var exampleResponse = ExampleResponseMapper.INSTANCE.mapEntityToResponse(exampleEntity);

        var exampleResponseList = List.of(exampleResponse);

        given(exampleFacade.listAllExample()).willReturn(exampleResponseList);

        var listExampleResponse = exampleController.allExample();

        Assertions.assertEquals(exampleResponseList, listExampleResponse);
    }

    @ParameterizedTest
    @ValueSource(longs = {1L})
    void shouldDeleteExampleEntityByIdTest(long id) {
        exampleController.deleteExample(id);

        then(exampleFacade).should(times(1)).deleteExampleById(id);
    }

}
