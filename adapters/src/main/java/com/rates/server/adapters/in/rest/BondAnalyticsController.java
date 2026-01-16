package com.rates.server.adapters.in.rest;

import com.rates.server.domain.services.BondAnalyticsService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bond-analytics")
public class BondAnalyticsController {
  private static final Logger logger = LoggerFactory.getLogger(BondAnalyticsController.class);

  private final BondAnalyticsService bondAnalyticsService;

  public BondAnalyticsController(BondAnalyticsService bondAnalyticsService) {
    this.bondAnalyticsService = bondAnalyticsService;
  }

  @PostMapping("/accrued-interest")
  @ResponseStatus(HttpStatus.OK)
  public AccruedInterestResponse calculateAccruedInterest(
      @Valid @RequestBody AccruedInterestRequest request) {
    logger.info("Calculating accrued interest for request: {}", request);

    double accruedInterest =
        bondAnalyticsService.calculateAccruedInterest(
            request.faceValue(),
            request.couponRate(),
            request.lastCouponDate(),
            request.settlementDate(),
            request.dayCountConvention());

    return new AccruedInterestResponse(
        request.faceValue(),
        request.couponRate(),
        request.lastCouponDate().toString(),
        request.settlementDate().toString(),
        request.dayCountConvention(),
        accruedInterest);
  }
}
