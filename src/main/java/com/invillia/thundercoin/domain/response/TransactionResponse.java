package com.invillia.thundercoin.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {
    private Double value;
    private String userAccount;
    private String origin;
    private Double valueQuotation;
    private String transactionType;
    private String dateTransaction;
}
