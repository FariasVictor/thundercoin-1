package com.invillia.thundercoin.repository;

import com.invillia.thundercoin.domain.Origin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OriginRepository extends JpaRepository<Origin, Long> {

    boolean existsByName(final String name);

    List<Origin> findByCategory(final String category);

}