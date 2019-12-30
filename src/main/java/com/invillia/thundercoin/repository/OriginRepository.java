package com.invillia.ThunderCoin.repository;

import com.invillia.ThunderCoin.domain.Origin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OriginRepository extends JpaRepository<Origin, Long> {

}