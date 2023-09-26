package com.dattran.fraud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FrauCheckHistoryRepository extends JpaRepository<FrauCheckHistory, Integer> {
}
