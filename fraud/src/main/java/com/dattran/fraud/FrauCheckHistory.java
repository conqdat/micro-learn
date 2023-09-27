package com.dattran.fraud;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class FrauCheckHistory {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer customerId;
    private Boolean isFraudster;
    private LocalDateTime createdAt;
}
