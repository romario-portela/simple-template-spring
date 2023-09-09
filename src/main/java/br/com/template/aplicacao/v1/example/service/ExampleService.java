package br.com.template.aplicacao.v1.example.service;

import br.com.template.aplicacao.domain.Constants;
import br.com.template.aplicacao.exception.domain.BusinessException;
import br.com.template.aplicacao.exception.errortype.GenericException;
import br.com.template.aplicacao.exception.util.ExceptionUtils;
import br.com.template.aplicacao.v1.example.domain.ExampleEntity;
import br.com.template.aplicacao.v1.example.repository.ExampleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ExampleService {

    private final ExampleRepository exampleRepository;

    private final ExampleValidateInsertService exampleValidateInsertService;

    public Optional<ExampleEntity> insert(ExampleEntity exampleEntity) {
        try {
            var validationResultDto = exampleValidateInsertService.validateExample(exampleEntity, exampleRepository);
            if (validationResultDto.isValid()) {
                return Optional.of(exampleRepository.save(exampleEntity));
            } else {
                log.error(validationResultDto.getErrorMessage());
                throw new BusinessException(validationResultDto.getErrorMessage());
            }
        } catch (BusinessException ex) {
            log.error(ex.getMessage());
            throw new BusinessException(ex.getMessage());
        } catch (Exception e) {
            throw ExceptionUtils.buildNotPersistedException(Constants.EXAMPLE_NOT_PERSISTED);
        }
    }

    public Optional<ExampleEntity> update(Long id, ExampleEntity exampleEntity) {
        exampleEntity.setId(id);

        try {
            if (exampleRepository.findById(id).isPresent()) {
                return Optional.of(exampleRepository.save(exampleEntity));
            }
        } catch (GenericException ex) {
            throw ex;
        } catch (Exception e) {
            throw ExceptionUtils.buildNotPersistedException(Constants.EXAMPLE_NOT_PERSISTED);
        }

        return Optional.empty();
    }

    public Optional<ExampleEntity> findById(Long id) {
        try {
            return exampleRepository.findById(id);
        } catch (GenericException ex) {
            throw ex;
        } catch (Exception e) {
            throw ExceptionUtils.buildNotPersistedException(Constants.EXAMPLE_NOT_FOUND);
        }
    }

    public void deleteExampleById(Long id) {
        try {
            var optionalExampleEntity = findById(id);

            if (optionalExampleEntity.isPresent()) {
                exampleRepository.deleteById(id);
            }
        } catch (Exception ignored) {
        }
    }

    public List<ExampleEntity> listAll() {
        return exampleRepository.findAll();
    }

    public void inactivate(Long id) {
        var example = findById(id);

        try {
            if (example.isPresent()) {
                var exampleInactivate = example.get();
                exampleInactivate.setActive(Boolean.FALSE);
                exampleRepository.save(exampleInactivate);
            }
        } catch (GenericException ex) {
            throw ex;
        } catch (Exception e) {
            throw ExceptionUtils.buildNotPersistedException(Constants.EXAMPLE_NOT_INACTIVATED);
        }
    }


    public void activate(Long id) {
        var example = findById(id);

        try {
            if (example.isPresent()) {
                var exampleInactivate = example.get();
                exampleInactivate.setActive(Boolean.TRUE);
                exampleRepository.save(exampleInactivate);
            }
        } catch (GenericException ex) {
            throw ex;
        } catch (Exception e) {
            throw ExceptionUtils.buildNotPersistedException(Constants.EXAMPLE_NOT_ACTIVATED);
        }
    }
}
