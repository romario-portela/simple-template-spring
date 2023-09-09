package br.com.template.aplicacao.v1.example.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExampleResponse {

    @ApiModelProperty(value = "Identificador do Exemplo", dataType = "java.lang.Long")
    private Long id;

    @ApiModelProperty(value = "Descrição do Exemplo", dataType = "java.lang.String")
    private String description;

    @ApiModelProperty(value = "Se o Exemplo está ativo", dataType = "java.lang.Boolean")
    private Boolean active;

    @ApiModelProperty(value = "Limite de usos do Exemplo", dataType = "java.lang.Integer")
    private Integer limitUses;

    @ApiModelProperty(value = "Data inicial", example = "20/01/2020 00:00:00")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime startDate;

    @ApiModelProperty(value = "Tipo de desconto do Exemplo", dataType = "java.lang.Short")
    private Short discountType;

    @ApiModelProperty(value = "Valor de venda do Exemplo", dataType = "java.math.BigDecimal")
    private BigDecimal price;
}