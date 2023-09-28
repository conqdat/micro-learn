package com.dattran.fraud;

import com.dattran.clients.fraud.FraudCheckResponse;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fraud-check")
@AllArgsConstructor
@Log4j2
public class FrauCheckHistoryController {

    private final FrauCheckHistoryService frauCheckHistoryService;

    @GetMapping({"/{customerId}"})
    public   FraudCheckResponse checkFraud(
            @PathVariable("customerId") Integer customerId
    ) {
        boolean isFraudulentCustomer = frauCheckHistoryService.isFraudster(customerId);
        log.info("Fraud check for customer {} is {}", customerId, isFraudulentCustomer);
        return new FraudCheckResponse(isFraudulentCustomer);
    }

}
