package com.invillia.thundercoin.repository;


import com.invillia.thundercoin.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByCpf(final String cpf);
}
