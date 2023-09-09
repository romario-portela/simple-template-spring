package br.com.template.aplicacao.v1.example.model.request;

import br.com.template.aplicacao.v1.example.domain.DiscountTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class ExampleRequest {

    @ApiModelProperty(value = "Descrição do Exemplo", required = true, dataType = "java.lang.String")
    @NotBlank(message = "{example.description.not.null}")
    @Size(max = 100, message = "{example.description.max.size}")
    private String description;

    @ApiModelProperty(value = "Se o Exemplo está ativo", required = true, dataType = "java.lang.Boolean")
    @NotNull(message = "{example.active.not.null}")
    private Boolean active;

    @ApiModelProperty(value = "Limite de usos do Exemplo", required = true, dataType = "java.lang.Integer")
    @NotNull(message = "{example.limit.not.null}")
    @Positive(message = "{example.limit.positive}")
    private Integer limitUses;

    @ApiModelProperty(value = "Data inicial do Exemplo", required = true, example = "20/01/2020 00:00:00")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @NotNull(message = "{example.start.date.not.null}")
    private LocalDateTime startDate;

    @ApiModelProperty(value = "Tipo de desconto do Exemplo", required = true, dataType = "br.com.template.aplicacao.v1.example.domain.DiscountTypeEnum")
    @NotNull(message = "{example.discount.type.not.null}")
    private DiscountTypeEnum discountType;

    @ApiModelProperty(value = "Valor de venda do Exemplo", dataType = "java.math.BigDecimal")
    @DecimalMin(value = "00.00", message = "{example.price.decimal.min}")
    @Digits(integer = 3, fraction = 2)
    private BigDecimal price;
}
