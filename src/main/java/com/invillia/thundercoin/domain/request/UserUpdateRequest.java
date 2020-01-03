package com.invillia.thundercoin.domain.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class UserUpdateRequest {

    @NotBlank
    private String name;

    @NotBlank
    private Double balance;

    @NotBlank
    private String status;

}
