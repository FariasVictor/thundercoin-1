package com.invillia.thundercoin.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSaveRequest {
    @NotBlank(message = "O campo Nome não pode estar em branco!")
    private String name;

    @NotBlank(message = "O campo CPF não pode estar em branco!")
    private String cpf;
}
