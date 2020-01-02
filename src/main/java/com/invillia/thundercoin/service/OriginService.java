package com.invillia.thundercoin.service;

import com.invillia.thundercoin.domain.request.OriginRequest;
import com.invillia.thundercoin.domain.response.OriginResponse;

import java.util.List;

public interface OriginService {

     Long create(OriginRequest originRequest);
     List<OriginResponse> findAll();
     OriginResponse findById(Long id);
     void delete(Long id);
     void update(Long id, OriginRequest originRequest);

}