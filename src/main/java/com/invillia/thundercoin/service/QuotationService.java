package com.invillia.thundercoin.service;

import com.invillia.thundercoin.domain.request.QuotationRequest;
import com.invillia.thundercoin.domain.response.QuotationResponse;

import java.util.List;


public interface QuotationService {
     Long save(QuotationRequest quotationRequest);
     List<QuotationResponse> findAll();
     QuotationResponse findById(Long id);
     void update(QuotationRequest quotationRequest, Long id);
}
