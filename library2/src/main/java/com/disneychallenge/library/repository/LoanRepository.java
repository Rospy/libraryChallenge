package com.disneychallenge.library.repository;

import com.disneychallenge.library.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, Integer> {

    LoanEntity findByEmail(String email);

}
