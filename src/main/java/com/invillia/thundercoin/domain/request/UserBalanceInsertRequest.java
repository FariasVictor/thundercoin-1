package com.invillia.ThunderCoin.domain.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class UserBalanceInsertRequest {

    @NotBlank
    private BigDecimal balance;

}
