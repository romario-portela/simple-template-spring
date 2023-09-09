package br.com.template.aplicacao.v1.example.provider;

import br.com.template.aplicacao.v1.example.fixture.ExampleFixture;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class ExampleRequestSuccessArgumentProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                // 1
                Arguments.of(
                        ExampleFixture.getExampleRequestSuccessWithDiscountTypeMessage()
                )
        );
    }
}

