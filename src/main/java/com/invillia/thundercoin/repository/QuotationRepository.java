package com.invillia.ThunderCoin.repository;

import com.invillia.ThunderCoin.domain.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotationRepository extends JpaRepository<Quotation,Long> {
}
