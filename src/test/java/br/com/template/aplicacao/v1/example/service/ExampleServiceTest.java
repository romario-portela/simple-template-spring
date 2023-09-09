package br.com.template.aplicacao.v1.example.service;

import br.com.template.aplicacao.exception.errortype.GenericException;
import br.com.template.aplicacao.v1.example.domain.ExampleEntity;
import br.com.template.aplicacao.v1.example.fixture.ExampleFixture;
import br.com.template.aplicacao.v1.example.provider.ExampleEntityInvalidDataArgumentProvider;
import br.com.template.aplicacao.v1.example.repository.ExampleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExampleServiceTest {

    @InjectMocks
    private ExampleService exampleService;

    @Mock
    private ExampleRepository exampleRepository;

    @Mock
    private ExampleValidateInsertService exampleValidateInsertService;

    @Test
    void mustSaveExampleEntityTest() {
        var expectedExampleEntity = ExampleFixture.getExampleEntity();
        var successValidationResultDto = ExampleFixture.getValidationResultDtoSucess();

        given(exampleValidateInsertService.validateExample(expectedExampleEntity, exampleRepository)).willReturn(successValidationResultDto);
        given(exampleRepository.save(expectedExampleEntity)).willReturn(expectedExampleEntity);

        var actualOptionalExampleEntity = exampleService.insert(expectedExampleEntity);

        Assertions.assertEquals(Boolean.TRUE, actualOptionalExampleEntity.isPresent());
        Assertions.assertEquals(actualOptionalExampleEntity.get(), expectedExampleEntity);
        then(exampleRepository).should(times(1)).save(expectedExampleEntity);
    }

    @Test
    void mustUpdateExampleEntityByIdTest() {
        var expectedExampleEntity = ExampleFixture.getExampleEntity();

        var expectedOptionalExampleEntity = Optional.of(expectedExampleEntity);

        given(exampleRepository.findById(1L)).willReturn(expectedOptionalExampleEntity);

        given(exampleRepository.save(expectedExampleEntity)).willReturn(expectedOptionalExampleEntity.get());

        var actualOptionalExampleEntity = exampleService.update(1L, expectedExampleEntity);

        Assertions.assertEquals(Boolean.TRUE, actualOptionalExampleEntity.isPresent());
        then(exampleRepository).should(times(1)).findById(1L);
        then(exampleRepository).should(times(1)).save(expectedExampleEntity);
    }

    @Test
    void shouldReturnedErrorWhenFindByIdReturnEmptyAtUpdateExampleTest() {
        var expectedExampleEntity = ExampleFixture.getExampleEntity();

        given(exampleRepository.findById(1L)).willReturn(Optional.empty());

        var actualOptionalExampleEntity = exampleService.update(1L, expectedExampleEntity);

        Assertions.assertEquals(Boolean.FALSE, actualOptionalExampleEntity.isPresent());
        then(exampleRepository).should(times(1)).findById(1L);
        then(exampleRepository).should(never()).save(any());
    }

    @Test
    void mustReturnedExampleEntityByIdTest() {
        var expectedExampleEntity = ExampleFixture.getExampleEntity();

        var expectedOptionalExampleEntity = Optional.of(expectedExampleEntity);

        given(exampleRepository.findById(1L)).willReturn(expectedOptionalExampleEntity);

        var actualOptionalExampleEntity = exampleService.findById(1L);

        Assertions.assertEquals(Boolean.TRUE, actualOptionalExampleEntity.isPresent());
        Assertions.assertEquals(actualOptionalExampleEntity.get(), expectedExampleEntity);
        then(exampleRepository).should(times(1)).findById(1L);
    }

    @Test
    void shouldReturnErrorWhenFindByIdReturnEmptyAtFindExampleTest() {
        given(exampleRepository.findById(1L)).willReturn(Optional.empty());

        var actualOptionalExampleEntity = exampleService.findById(1L);

        Assertions.assertEquals(Boolean.FALSE, actualOptionalExampleEntity.isPresent());
        then(exampleRepository).should(times(1)).findById(1L);
        then(exampleRepository).should(never()).save((any()));
    }

    @Test
    void mustFieldActivateToTrueAtTableExampleEntityTest() {
        var exampleEntity = ExampleFixture.getExampleEntityInactivated();
        var expectedExampleEntity = ExampleFixture.getExampleEntity();

        given(exampleRepository.findById(1L)).willReturn(Optional.of(exampleEntity));

        var exampleEntityCapture = ArgumentCaptor.forClass(ExampleEntity.class);

        given(exampleRepository.save(exampleEntityCapture.capture())).willReturn(expectedExampleEntity);

        exampleService.activate(1L);

        then(exampleRepository).should(times(1)).findById(1L);
        then(exampleRepository).should(times(1)).save(expectedExampleEntity);
    }

    @Test
    void shouldNotActiveTheExampleEntityTest() {
        given(exampleRepository.findById(1L)).willReturn(Optional.empty());

        exampleService.activate(1L);

        then(exampleRepository).should(times(1)).findById(1L);
        then(exampleRepository).should(never()).save(any());
    }

    @Test
    void mustDeactivateTheExampleTest() {
        var mockExampleEntity = ExampleFixture.getExampleEntity();
        var expectedExampleEntity = ExampleFixture.getExampleEntityInactivated();

        given(exampleRepository.findById(1L)).willReturn(Optional.of(mockExampleEntity));

        var exampleEntityCapture = ArgumentCaptor.forClass(ExampleEntity.class);

        given(exampleRepository.save(exampleEntityCapture.capture())).willReturn(expectedExampleEntity);

        exampleService.inactivate(1L);

        then(exampleRepository).should(times(1)).findById(1L);
        then(exampleRepository).should(times(1)).save(expectedExampleEntity);
    }

    @Test
    void shouldNotDeactivatedExampleEntityTest() {
        given(exampleRepository.findById(1L)).willReturn(Optional.empty());

        exampleService.inactivate(1L);

        then(exampleRepository).should(times(1)).findById(1L);
        then(exampleRepository).should(never()).save(any());
    }

    @ParameterizedTest
    @ArgumentsSource(ExampleEntityInvalidDataArgumentProvider.class)
    void shouldNotInsertExampleEntityTest(ExampleEntity exampleEntity) {
        try {
            exampleService.insert(exampleEntity);
            fail("didn't throw an exception!");
        } catch (GenericException e) {
            // Test succeded!
        }

        verify(exampleRepository, never()).save(exampleEntity);
    }

    @ParameterizedTest
    @ArgumentsSource(ExampleEntityInvalidDataArgumentProvider.class)
    void unsuccessfulToUpdateExample(ExampleEntity exampleEntity) {
        given(exampleRepository.findById(1L)).willReturn(Optional.of(exampleEntity));

        try {
            exampleService.update(1L, exampleEntity);
            fail("didn't throw an exception!");
        } catch (GenericException e) {
            // Test succeded!
        }

        verify(exampleRepository, times(1)).findById(1L);
    }

    @Test
    void mustReturnedAllExampleEntityTest() {
        var exampleEntity = ExampleFixture.getExampleEntity();

        when(exampleRepository.findAll()).thenReturn(List.of(exampleEntity));

        var listExampleEntity = exampleService.listAll();

        verify(exampleRepository, times(1)).findAll();
        Assertions.assertEquals(1L, listExampleEntity.size());
    }

    @Test
    void shouldReturnNoContentExceptionWhenTheExampleEntityIsNoContentTest() {
        given(exampleRepository.findAll()).willReturn(List.of());

        var listExampleEntity = exampleService.listAll();

        verify(exampleRepository, times(1)).findAll();
        Assertions.assertEquals(0L, listExampleEntity.size());
    }

    @Test
    void shouldDeleteExampleEntityByIdTest() {
        var exampleEntity = ExampleFixture.getExampleEntity();

        when(exampleRepository.findById(1L)).thenReturn(Optional.of(exampleEntity));

        exampleService.deleteExampleById(1L);

        verify(exampleRepository, times(1)).deleteById(1L);
    }

    @Test
    void shouldNotDeleteExampleEntityWhenInvalidId() {
        when(exampleRepository.findById(1L)).thenReturn(Optional.empty());

        exampleService.deleteExampleById(2L);

        verify(exampleRepository, never()).deleteById(anyLong());
    }
}
