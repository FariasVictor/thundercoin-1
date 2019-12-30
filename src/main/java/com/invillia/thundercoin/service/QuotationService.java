package com.invillia.ThunderCoin.service;

import com.invillia.ThunderCoin.domain.request.QuotationRequest;
import com.invillia.ThunderCoin.domain.response.QuotationResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface QuotationService {
     Long save(QuotationRequest quotationRequest);
     List<QuotationResponse> findAll();
     QuotationResponse findById(Long id);
     void update(QuotationRequest quotationRequest, Long id);
     void delete(Long id);
}
