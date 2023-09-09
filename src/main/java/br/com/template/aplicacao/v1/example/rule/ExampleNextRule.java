package br.com.template.aplicacao.v1.example.rule;

import br.com.template.aplicacao.v1.example.domain.ExampleEntity;
import br.com.template.aplicacao.v1.example.repository.ExampleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class ExampleNextRule implements ExampleRule {

    @Override
    public void validate(ExampleEntity exampleEntity, ExampleRepository exampleRepository) {
        log.info("Validação efetuada com sucesso");
    }
}
