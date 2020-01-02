package com.invillia.thundercoin.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {

    @NotNull
    private Long id;

    @NotBlank
    private Long userId;

    @NotNull
    private Double balance;
}
