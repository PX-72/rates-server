package com.rates.server.domain.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BondAnalyticsService {
  private static final Logger logger = LoggerFactory.getLogger(BondAnalyticsService.class);

  public double calculateAccruedInterest(
      double faceValue,
      double couponRate,
      LocalDate lastCouponDate,
      LocalDate settlementDate,
      String dayCountConvention) {

    logger.info(
        "Calculating accrued interest for faceValue={}, couponRate={}, lastCouponDate={},"
            + " settlementDate={}, dayCountConvention={}",
        faceValue,
        couponRate,
        lastCouponDate,
        settlementDate,
        dayCountConvention);

    long actualDays = ChronoUnit.DAYS.between(lastCouponDate, settlementDate);

    double accruedInterest =
        switch (dayCountConvention) {
          case "30/360" -> {
            long days30_360 = dayCount30_360(lastCouponDate, settlementDate);
            yield (faceValue * couponRate * days30_360) / (360 * 2);
          }
          case "ACT/360" -> (faceValue * couponRate * actualDays) / (360 * 2);
          case "ACT/365" -> (faceValue * couponRate * actualDays) / (365 * 2);
          case "ACT/ACT" -> {
            int daysInYear = lastCouponDate.isLeapYear() ? 366 : 365;
            yield (faceValue * couponRate * actualDays) / (daysInYear * 2);
          }
          default -> throw new IllegalArgumentException(
              "Unsupported day count convention: " + dayCountConvention);
        };

    logger.info("Calculated accrued interest: {}", accruedInterest);
    return accruedInterest;
  }

  private long dayCount30_360(LocalDate startDate, LocalDate endDate) {
    int d1 = Math.min(startDate.getDayOfMonth(), 30);
    int d2 = endDate.getDayOfMonth();
    if (d1 == 30 && d2 == 31) {
      d2 = 30;
    }
    return (endDate.getYear() - startDate.getYear()) * 360L
        + (endDate.getMonthValue() - startDate.getMonthValue()) * 30L
        + (d2 - d1);
  }
}
