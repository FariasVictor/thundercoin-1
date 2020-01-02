package com.invillia.thundercoin.repository;

import com.invillia.thundercoin.domain.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotationRepository extends JpaRepository<Quotation,Long> {
}
