package com.invillia.thundercoin.domain.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserUpdateRequest {
    @NotBlank(message = "O campo Nome não pode estar em branco!")
    private String name;

    @NotBlank(message = "O campo CPF não pode estar em branco!")
    private String cpf;
}
