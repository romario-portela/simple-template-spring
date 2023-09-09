package br.com.template.aplicacao.v1.example;

import br.com.template.aplicacao.exception.response.ErrorInfo;
import br.com.template.aplicacao.v1.example.model.request.ExampleRequest;
import br.com.template.aplicacao.v1.example.model.response.ExampleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/example")
@Api(tags = "Example Controller")
public class ExampleController {

    private final ExampleFacade exampleFacade;

    @PostMapping
    @ApiOperation(code = 201, value = "Create a Example", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Example created successfully", response = ExampleResponse.class),
            @ApiResponse(code = 400, message = "Informed data are invalid", response = ErrorInfo.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorInfo.class)
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    public ExampleResponse createExample(@Valid @RequestBody ExampleRequest exampleRequest) {
        return exampleFacade.insert(exampleRequest);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a Example", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Example updated successfully", response = ExampleResponse.class),
            @ApiResponse(code = 400, message = "Informed data are invalid", response = ErrorInfo.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorInfo.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorInfo.class)
    })
    public ExampleResponse updateExample(@PathVariable(name = "id") Long id, @Valid @RequestBody ExampleRequest exampleRequest) {
        return exampleFacade.update(id, exampleRequest);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find Example by id", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success to find example", response = ExampleResponse.class),
            @ApiResponse(code = 400, message = "Informed data are invalid", response = ErrorInfo.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorInfo.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorInfo.class)
    })
    public ExampleResponse getExample(@PathVariable("id") Long id) {
        return exampleFacade.findById(id);
    }

    @GetMapping
    @ApiOperation(value = "Find All Example", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Example found", response = ExampleResponse.class),
            @ApiResponse(code = 204, message = "No content"),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorInfo.class)
    })
    public List<ExampleResponse> allExample() {
        return exampleFacade.listAllExample();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(code = 204, value = "Delete Example by id", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Example deleted"),
            @ApiResponse(code = 404, message = "Example Not found", response = ErrorInfo.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorInfo.class)
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExample(@PathVariable("id") Long id) {
        exampleFacade.deleteExampleById(id);
    }

    @PatchMapping("/{id}/inactivate")
    @ApiOperation(code = 204, value = "Inactivate Example", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Example inactivate successfully"),
            @ApiResponse(code = 404, message = "Example data not found", response = ErrorInfo.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ErrorInfo.class)
    })
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void inactivateExample(@PathVariable(name = "id") Long id) {
        exampleFacade.inactivateExample(id);
    }

    @PatchMapping("/{id}/activate")
    @ApiOperation(code = 204, value = "Activate Example", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Example activated successfully"),
            @ApiResponse(code = 404, message = "Example data not found", response = ErrorInfo.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ErrorInfo.class)
    })
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void activateExample(@PathVariable(name = "id") Long id) {
        exampleFacade.activateExample(id);
    }
}
