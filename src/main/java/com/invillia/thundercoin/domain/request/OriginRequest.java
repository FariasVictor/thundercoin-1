package com.invillia.thundercoin.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OriginRequest {

    @NotNull(message = "O campo não pode ser nulo")
    @NotBlank(message = "O campo não pode estar em branco")
    private String name;

    @NotNull(message = "O campo não pode ser nulo")
    private boolean active;

    @NotNull(message = "O campo não pode ser nulo")
    @NotBlank(message = "O campo não pode estar em branco")
    private String originType;

}
