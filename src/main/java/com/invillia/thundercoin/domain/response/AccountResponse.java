package com.invillia.thundercoin.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {
    private Long id;
    private String nameUser;
    private Double balance;
    private String status;
    private String createdAt;
    private String updatedAt;
}
