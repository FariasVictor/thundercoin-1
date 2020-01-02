package com.invillia.thundercoin.service.impl;

import com.invillia.thundercoin.domain.Origin;
import com.invillia.thundercoin.domain.Quotation;
import com.invillia.thundercoin.domain.Transaction;
import com.invillia.thundercoin.domain.request.QuotationRequest;
import com.invillia.thundercoin.domain.response.QuotationResponse;
import com.invillia.thundercoin.exception.ObjectNotFoundException;
import com.invillia.thundercoin.exception.OriginTypeNotFoundException;
import com.invillia.thundercoin.exception.ValueNotAllowed;
import com.invillia.thundercoin.mapper.QuotationMapper;
import com.invillia.thundercoin.repository.QuotationRepository;
import com.invillia.thundercoin.service.QuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuotationServiceImpl implements QuotationService {

    private QuotationRepository quotationRepository;
    private QuotationMapper quotationMapper;

    @Autowired
    public QuotationServiceImpl(QuotationRepository quotationRepository, QuotationMapper quotationMapper) {
        this.quotationRepository = quotationRepository;
        this.quotationMapper = quotationMapper;
    }

    @Override
    public Long save(QuotationRequest quotationRequest) {
        Quotation quotation = quotationMapper.quotationRequestToQuotation(quotationRequest);
        if(quotation.getValue()< 0){
            throw new ValueNotAllowed("Cotação deve ser positiva");
        }
        return quotationRepository.save(quotation).getId();
    }

    @Override
    public List<QuotationResponse> findAll() {
        final List<Quotation> quotations = quotationRepository.findAll();

        return quotationMapper.quotationToQuotationResponse(quotations);
    }

    @Override
    public QuotationResponse findById(Long id) {
        final Quotation quotation = quotationRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                "Cotação não encontrada!"
        ));
        return quotationMapper.quotationToQuotationResponse(quotation);
    }

    @Override
    public void update(QuotationRequest quotationRequest, Long id) {
        final Quotation quotation = quotationRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                "Cotação não encontrada!"
        ));
        quotationMapper.updateQuotationByQuotationRequest(quotation, quotationRequest);
        quotationRepository.save(quotation);
    }
}
