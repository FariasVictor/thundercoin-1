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
      return OriginResponse.builder()
              .id(origin.getId())
              .name(origin.getName())
              .category(origin.getCategory())
              .address(origin.getAddress())
              .imagePath(origin.getImagePath())
              .status(origin.getStatus().toString())
              .createdAt(origin.getCreatedAt().format(formatter))
              .updatedAt(origin.getUpdatedAt().format(formatter))
              .build();
   }

   public List<OriginResponse> originToOriginResponse(final List<Origin> origins) {
      return origins.stream().map(this::originToOriginResponse).collect(Collectors.toList());
   }

   public Origin originRequestToOrigin(final OriginRequest originRequest) {
      Origin origin = new Origin();

      origin.setName(originRequest.getName());
      origin.setCategory(originRequest.getCategory());
      origin.setAddress(originRequest.getAddress());
      origin.setImagePath(originRequest.getImagePath());

      return origin;
   }

   public void updateOriginByAccountRequest(final Origin origin, final OriginRequest originRequest) {
      origin.setName(originRequest.getName());
      origin.setCategory(originRequest.getCategory());
      origin.setAddress(originRequest.getAddress());
      origin.setImagePath(originRequest.getImagePath());
   }
}
