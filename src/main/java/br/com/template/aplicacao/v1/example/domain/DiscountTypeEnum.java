package br.com.template.aplicacao.v1.example.domain;

import br.com.template.aplicacao.exception.util.ExceptionUtils;
import br.com.template.aplicacao.exception.util.MessageResource;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum DiscountTypeEnum {

    PRICE((short) 1),
    MESSAGE((short) 2);

    private final Short value;

    public static DiscountTypeEnum fromValue(Short discountTypeCode) {
        var message = MessageResource.getInstance().getMessage("example.discount.type.invalid");
        return Arrays.stream(DiscountTypeEnum.values())
                .filter(it -> it.getValue().equals(discountTypeCode))
                .findFirst()
                .orElseThrow(() -> ExceptionUtils.buildBadRequestException(message));
    }
}
