package br.com.template.aplicacao.v1.example;

import br.com.template.aplicacao.v1.example.mapper.ExampleRequestMapper;
import br.com.template.aplicacao.v1.example.mapper.ExampleResponseMapper;
import br.com.template.aplicacao.v1.example.model.request.ExampleRequest;
import br.com.template.aplicacao.v1.example.model.response.ExampleResponse;
import br.com.template.aplicacao.v1.example.service.ExampleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ExampleFacade {

    private final ExampleService exampleService;

    public ExampleResponse insert(ExampleRequest exampleRequest) {
        var exampleEntity = ExampleRequestMapper.INSTANCE.mapModelToEntity(exampleRequest);

        return exampleService.insert(exampleEntity)
                .map(ExampleResponseMapper.INSTANCE::mapEntityToResponse)
                .orElseThrow();
    }

    public ExampleResponse update(Long id, ExampleRequest exampleRequest) {
        var exampleEntity = ExampleRequestMapper.INSTANCE.mapModelToEntity(exampleRequest);

        return exampleService.update(id, exampleEntity)
                .map(ExampleResponseMapper.INSTANCE::mapEntityToResponse)
                .orElseThrow();
    }

    public ExampleResponse findById(Long id) {
        return exampleService.findById(id)
                .map(ExampleResponseMapper.INSTANCE::mapEntityToResponse)
                .orElseThrow();
    }

    public List<ExampleResponse> listAllExample() {
        return exampleService.listAll()
                .stream().map(ExampleResponseMapper.INSTANCE::mapEntityToResponse)
                .collect(Collectors.toList());
    }

    public void deleteExampleById(Long id) {
        exampleService.deleteExampleById(id);
    }

    public void inactivateExample(Long id) {
        exampleService.inactivate(id);
    }

    public void activateExample(Long id) {
        exampleService.activate(id);
    }
}
