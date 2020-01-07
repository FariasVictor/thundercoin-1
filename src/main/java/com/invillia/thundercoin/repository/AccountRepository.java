package com.invillia.thundercoin.repository;

import com.invillia.thundercoin.domain.Account;
import com.invillia.thundercoin.enums.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("select a from Account a inner join User u on u.id = a.id and u.status = ?1")
    List<Account> findAllByUserStatus(final StatusEnum statusEnum);

    boolean existsAccountByUserId(final Long id);
}
