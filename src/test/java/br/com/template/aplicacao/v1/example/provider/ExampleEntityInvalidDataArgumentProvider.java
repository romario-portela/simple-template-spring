package br.com.template.aplicacao.v1.example.provider;

import br.com.template.aplicacao.v1.example.fixture.ExampleFixture;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class ExampleEntityInvalidDataArgumentProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(
                        ExampleFixture.getExampleEntityWithPriceNull()
                ),
                Arguments.of(
                        ExampleFixture.getExampleEntityWithDiscTypeFixedPriceWithPriceNull()
                ),
                Arguments.of(
                        ExampleFixture.getExampleEntityWithDiscTypeMessageWithPriceNonNull()
                )
        );
    }
}
