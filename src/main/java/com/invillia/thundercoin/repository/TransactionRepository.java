package com.invillia.ThunderCoin.repository;

import com.invillia.ThunderCoin.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository  extends JpaRepository<Transaction, Long> {
}
