package com.invillia.ThunderCoin.service;

import com.invillia.ThunderCoin.domain.request.OriginRequest;
import com.invillia.ThunderCoin.domain.response.OriginResponse;

import java.util.List;

public interface OriginService {

     Long create(OriginRequest originRequest);

     List<OriginResponse> findAll();

     OriginResponse findById(Long id);

     void delete(Long id);

     void update(Long id, OriginRequest originRequest);

}