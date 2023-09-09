package br.com.template.aplicacao.v1.example.rule;

import br.com.template.aplicacao.v1.example.domain.ExampleEntity;
import br.com.template.aplicacao.v1.example.repository.ExampleRepository;

public interface ExampleRule {
    void validate(ExampleEntity exampleEntity, ExampleRepository exampleRepository);
}
