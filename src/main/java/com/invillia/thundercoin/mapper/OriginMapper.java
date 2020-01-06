package com.invillia.thundercoin.mapper;

import com.invillia.thundercoin.domain.Origin;
import com.invillia.thundercoin.domain.request.OriginRequest;
import com.invillia.thundercoin.domain.response.OriginResponse;
import com.invillia.thundercoin.enums.TransactionType;
import com.invillia.thundercoin.exception.OriginTypeNotFoundException;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OriginMapper {

   private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

   public OriginResponse originToOriginResponse(final Origin origin) {

      OriginResponse originResponse = new OriginResponse();

      originResponse.setId(origin.getId());
      originResponse.setName(origin.getName());
      originResponse.setCreatedAt(origin.getCreatedAt().format(formatter));
      originResponse.setUpdatedAt(origin.getUpdatedAt().format(formatter));

      return originResponse;
   }

   public List<OriginResponse> originToOriginResponse(final List<Origin> origins) {

     return origins.stream()
               .map(this::originToOriginResponse)
               .collect(Collectors.toList());

   }

   public Origin originRequestToOrigin(final OriginRequest originRequest) {

      Origin origin = new Origin();
      origin.setName(originRequest.getName());
      return origin;

   }

   public void updateOriginByAccountRequest(final Origin origin, final OriginRequest originRequest) {
      origin.setName(originRequest.getName());
   }

}
