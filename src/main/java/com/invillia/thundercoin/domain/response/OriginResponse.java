package com.invillia.thundercoin.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OriginResponse {

    private Long id;

    private String name;

    private boolean active;

    private String createdAt;

    private String updatedAt;

}
