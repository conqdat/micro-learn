package com.dattran.fraud;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fraud-check")
@AllArgsConstructor
public class FrauCheckHistoryController {

    private final FrauCheckHistoryService frauCheckHistoryService;

    @GetMapping({"/{customerId}"})
    public FraudCheckResponse checkFraud(
            @PathVariable("customerId") Integer customerId
    ) {
        boolean isFraudulentCustomer = frauCheckHistoryService.isFraudster(customerId);
        return new FraudCheckResponse(isFraudulentCustomer);
    }

}
