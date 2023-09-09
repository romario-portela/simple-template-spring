package br.com.template.aplicacao.v1.example.rule;

import br.com.template.aplicacao.exception.domain.BusinessException;
import br.com.template.aplicacao.v1.example.domain.ExampleEntity;
import br.com.template.aplicacao.v1.example.repository.ExampleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class ExampleActiveRule implements ExampleRule {

    private ExampleNextRule nextRule;

    public void validate(ExampleEntity exampleEntity, ExampleRepository exampleRepository) {
        try {
            log.info("Iniciando a validação do campo Example: " + exampleEntity);
            validateExampleIsActive(exampleEntity);

            nextRule.validate(exampleEntity, exampleRepository);
        } catch (BusinessException e) {
            throw new BusinessException("O registro não atende aos requisitos mínimos de validação! Erro: " + e.getMessage());
        }
    }

    private void validateExampleIsActive(ExampleEntity exampleEntity) {
        if (exampleEntity.getActive()) {
            log.info("Validação efetuada com sucesso");
        } else {
            throw new BusinessException("O example não está setado como active = true!");
        }
    }
}
