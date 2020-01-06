package com.invillia.thundercoin.service;

import com.invillia.thundercoin.domain.request.QuotationRequest;
import com.invillia.thundercoin.domain.response.QuotationResponse;

import java.util.List;


public interface QuotationService {
     List<QuotationResponse> findAll();
     QuotationResponse findById(Long id);
     Long save(QuotationRequest quotationRequest);
}
