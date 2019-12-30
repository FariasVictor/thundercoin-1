package com.invillia.ThunderCoin.repository;


import com.invillia.ThunderCoin.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByCpf(final String cpf);
}
