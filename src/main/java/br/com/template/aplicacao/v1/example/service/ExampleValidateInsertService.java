package br.com.template.aplicacao.v1.example.service;

import br.com.template.aplicacao.domain.ValidationResultDto;
import br.com.template.aplicacao.exception.domain.BusinessException;
import br.com.template.aplicacao.v1.example.domain.ExampleEntity;
import br.com.template.aplicacao.v1.example.repository.ExampleRepository;
import br.com.template.aplicacao.v1.example.rule.ExampleActiveRule;
import br.com.template.aplicacao.v1.example.rule.ExampleNextRule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ExampleValidateInsertService {
    public ValidationResultDto validateExample(ExampleEntity exampleEntity, ExampleRepository exampleRepository) {
        return validateExampleAccordingToRules(exampleEntity, exampleRepository);
    }

    private ValidationResultDto validateExampleAccordingToRules(ExampleEntity exampleEntity, ExampleRepository exampleRepository) {
        var validationResultDto = ValidationResultDto.builder().build();
        try {
            var exampleNextRule = new ExampleNextRule();
            var exampleActiveRule = new ExampleActiveRule(exampleNextRule);
            exampleActiveRule.validate(exampleEntity, exampleRepository);
            validationResultDto.makeValid();
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
            validationResultDto.makeInvalid(e.getMessage());
        }
        return validationResultDto;
    }
}
