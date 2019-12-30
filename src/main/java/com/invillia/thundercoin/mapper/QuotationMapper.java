package com.invillia.ThunderCoin.mapper;

import com.invillia.ThunderCoin.domain.Quotation;
import com.invillia.ThunderCoin.domain.request.QuotationRequest;
import org.springframework.stereotype.Component;

@Component
public class QuotationMapper {
    public Quotation quotationRequestToQuotation(QuotationRequest quotationRequest){
        Quotation quotation= new Quotation();
        quotation.setValue(quotationRequest.getValue());
        quotation.setActive(quotationRequest.getActive());
        return quotation;
    }
}
