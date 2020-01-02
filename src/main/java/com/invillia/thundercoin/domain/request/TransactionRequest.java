package com.invillia.thundercoin.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {

    @NotNull(message = "O campo não pode ser nulo")
    private Double value;

    @NotNull(message = "O campo não pode ser nulo")
    @NotBlank(message = "O campo não pode estar em branco")
    private Long userId;

    @NotNull(message = "O campo não pode ser nulo")
    @NotBlank(message = "O campo não pode estar em branco")
    private Long originId;

    @NotNull(message = "O campo não pode ser nulo")
    @NotBlank(message = "O campo não pode estar em branco")
    private Long quotationId;
}
