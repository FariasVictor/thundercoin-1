package com.invillia.thundercoin.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuotationRequest {

    @NotNull(message = "Valor não pode ser nulo")
    @PositiveOrZero(message = "Valor deve ser maior ou igual a 0")
    private Double value;

    private Boolean active=true;

}
