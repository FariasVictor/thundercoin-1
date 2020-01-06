package com.invillia.thundercoin.service.impl;

import com.invillia.thundercoin.domain.Origin;
import com.invillia.thundercoin.domain.Quotation;
import com.invillia.thundercoin.domain.request.QuotationRequest;
import com.invillia.thundercoin.domain.response.QuotationResponse;
import com.invillia.thundercoin.enums.StatusEnum;
import com.invillia.thundercoin.exception.ObjectNotFoundException;
import com.invillia.thundercoin.exception.ValueNotAllowed;
import com.invillia.thundercoin.mapper.QuotationMapper;
import com.invillia.thundercoin.repository.QuotationRepository;
import com.invillia.thundercoin.service.QuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuotationServiceImpl implements QuotationService {

    private QuotationRepository quotationRepository;
    private QuotationMapper quotationMapper;

    public QuotationServiceImpl(QuotationRepository quotationRepository, QuotationMapper quotationMapper) {
        this.quotationRepository = quotationRepository;
        this.quotationMapper = quotationMapper;
    }

    @Transactional
    public List<QuotationResponse> findAll() {
        final List<Quotation> quotations = quotationRepository.findAll();

        return quotationMapper.quotationToQuotationResponse(quotations);
    }

    @Transactional
    public QuotationResponse findById(Long id) {
        final Quotation quotation = quotationRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                "Cotação não encontrada!"
        ));
        return quotationMapper.quotationToQuotationResponse(quotation);
    }

    @Transactional
    public Long save(QuotationRequest quotationRequest) {
        Quotation quotationActive = quotationRepository.findByStatus(StatusEnum.ACTIVE);

        if(quotationActive != null){
            quotationActive.setStatus(StatusEnum.DISABLED);

            quotationRepository.save(quotationActive);
        }

        Quotation quotation = quotationMapper.quotationRequestToQuotation(quotationRequest);

        if(quotation.getValue()< 0){
            throw new ValueNotAllowed("Cotação deve ser positiva");
        }

        return quotationRepository.save(quotation).getId();
    }
}
