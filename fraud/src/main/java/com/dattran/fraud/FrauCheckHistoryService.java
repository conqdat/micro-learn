package com.dattran.fraud;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FrauCheckHistoryService {

    private final FrauCheckHistoryRepository frauCheckHistoryRepository;

    public boolean isFraudster(Integer customerId) {
        frauCheckHistoryRepository.save(
                FrauCheckHistory.builder()
                        .customerId(customerId)
                        .isFraudster(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return false;
    }


}
