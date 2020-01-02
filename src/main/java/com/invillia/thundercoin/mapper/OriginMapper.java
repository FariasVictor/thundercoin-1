package com.invillia.thundercoin.mapper;

import com.invillia.thundercoin.domain.Origin;
import com.invillia.thundercoin.domain.request.OriginRequest;
import com.invillia.thundercoin.domain.response.OriginResponse;
import com.invillia.thundercoin.enums.OriginType;
import com.invillia.thundercoin.exception.OriginTypeNotFoundException;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OriginMapper {

   private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

   public OriginResponse originToOriginResponse(final Origin origin) {

      return OriginResponse.builder()
              .id(origin.getId())
              .name(origin.getName())
              .active(origin.isActive())
              .originType(origin.getOriginType().toString())
              .createdAt(origin.getCreatedAt().format(formatter))
              .updatedAt(origin.getUpdatedAt().format(formatter))
              .build();

   }

   public List<OriginResponse> originToOriginResponse(final List<Origin> origins) {
     return origins.stream()
               .map(this::originToOriginResponse)
               .collect(Collectors.toList());

   }

   public Origin originRequestToOrigin(final OriginRequest originRequest) {

      Origin origin = new Origin();

      origin.setName(originRequest.getName());
      origin.setActive(originRequest.isActive());

      if (originRequest.getOriginType() != null && !originRequest.getOriginType().isBlank()) {
         try {
            origin.setOriginType(OriginType.valueOf(originRequest.getOriginType()));
         } catch (Exception e) {
            throw new OriginTypeNotFoundException("Tipo não encontrado: " + e.getMessage());
         }
      }

      return origin;
   }

   public void updateOriginByAccountRequest(final Origin origin, final OriginRequest originRequest) {

      origin.setName(originRequest.getName());
      origin.setActive(originRequest.isActive());
      origin.setOriginType(OriginType.valueOf(originRequest.getOriginType()));
   }

}
