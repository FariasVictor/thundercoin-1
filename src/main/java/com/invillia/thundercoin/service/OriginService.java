package com.invillia.thundercoin.service;

import com.invillia.thundercoin.domain.request.OriginRequest;
import com.invillia.thundercoin.domain.response.OriginResponse;

import java.util.List;

public interface OriginService {
     List<OriginResponse> findAll();
     OriginResponse findById(Long id);
     Long create(OriginRequest originRequest);
     void update(Long id, OriginRequest originRequest);
     void delete(Long id);
}