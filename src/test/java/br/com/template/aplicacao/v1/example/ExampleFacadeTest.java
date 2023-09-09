package br.com.template.aplicacao.v1.example;

import br.com.template.aplicacao.v1.example.fixture.ExampleFixture;
import br.com.template.aplicacao.v1.example.mapper.ExampleRequestMapper;
import br.com.template.aplicacao.v1.example.mapper.ExampleResponseMapper;
import br.com.template.aplicacao.v1.example.service.ExampleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Slf4j
@ExtendWith(MockitoExtension.class)
class ExampleFacadeTest {

    @InjectMocks
    private ExampleFacade exampleFacade;

    @Mock
    private ExampleService exampleService;

    @Test
    void checksCallToInsertExampleEntityTest() {
        var exampleRequest = ExampleFixture.getExampleRequest();

        var exampleEntity = ExampleRequestMapper.INSTANCE.mapModelToEntity(exampleRequest);

        var expectedExampleResponse = ExampleResponseMapper.INSTANCE.mapEntityToResponse(exampleEntity);

        given(exampleService.insert(exampleEntity)).willReturn(Optional.of(exampleEntity));

        var actualExampleResponse = exampleFacade.insert(exampleRequest);

        Assertions.assertEquals(actualExampleResponse, expectedExampleResponse);
        then(exampleService).should(times(1)).insert(exampleEntity);
    }

    @Test
    void checksCallToUpdateExampleEntityTest() {
        var exampleRequest = ExampleFixture.getExampleRequest();

        var exampleEntity = ExampleRequestMapper.INSTANCE.mapModelToEntity(exampleRequest);

        var expectedExampleResponse = ExampleResponseMapper.INSTANCE.mapEntityToResponse(exampleEntity);
        expectedExampleResponse.setId(1L);

        given(exampleService.update(1L, exampleEntity))
                .willReturn(Optional.of(ExampleFixture.getExampleEntityUpdate()));

        var actualExampleResponse = exampleFacade.update(1L, exampleRequest);

        Assertions.assertEquals(actualExampleResponse, expectedExampleResponse);
        then(exampleService).should(times(1)).update(1L, exampleEntity);
    }

    @Test
    void checksCallToFindExampleEntityByIdTest() {
        var exampleEntity = ExampleFixture.getExampleEntity();

        var expectedExampleResponse = ExampleResponseMapper.INSTANCE.mapEntityToResponse(exampleEntity);

        var expectedMonoExampleEntity = Optional.of(exampleEntity);

        given(exampleService.findById(1L)).willReturn(expectedMonoExampleEntity);

        var actualExampleResponse = exampleFacade.findById(1L);

        Assertions.assertEquals(actualExampleResponse, expectedExampleResponse);
        then(exampleService).should(times(1)).findById(1L);
    }

    @Test
    void checksCallDeactivateExampleEntityTest() {
        exampleFacade.inactivateExample(1L);

        then(exampleService).should(times(1)).inactivate(1L);
    }

    @Test
    void checksNotCallInactivateTheExampleEntityTest() {
        exampleFacade.inactivateExample(1L);

        then(exampleService).should(times(1)).inactivate(1L);
    }

    @Test
    void checksCallActivateTheExampleEntityTest() {
        exampleFacade.activateExample(1L);

        then(exampleService).should(times(1)).activate(1L);
    }

    @Test
    void checksNotCallMethodActivateTest() {
        exampleFacade.activateExample(1L);

        then(exampleService).should(times(1)).activate(1L);
    }

    @Test
    void shouldListAllExampleEntityTest() {
        var exampleEntity = ExampleFixture.getExampleEntity();

        var exampleResponse = ExampleResponseMapper.INSTANCE.mapEntityToResponse(exampleEntity);

        given(exampleService.listAll()).willReturn(List.of(exampleEntity));

        var exampleResponseList = exampleFacade.listAllExample();

        Assertions.assertEquals(1L, exampleResponseList.size());
        Assertions.assertEquals(exampleResponseList.get(0), exampleResponse);
        then(exampleService).should(times(1)).listAll();
    }

    @Test
    void shouldDeleteExampleEntityById() {
        exampleFacade.deleteExampleById(1L);

        verify(exampleService, times(1)).deleteExampleById(1L);
    }
}