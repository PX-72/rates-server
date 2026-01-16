package com.rates.server.domain.services;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BondAnalyticsServiceTest {

  private BondAnalyticsService service;

  @BeforeEach
  void setUp() {
    service = new BondAnalyticsService();
  }

  @Test
  void calculateAccruedInterest_withAct365() {
    double result =
        service.calculateAccruedInterest(
            1000.0, 0.05, LocalDate.of(2024, 6, 15), LocalDate.of(2024, 9, 15), "ACT/365");

    // 92 actual days, (1000 * 0.05 * 92) / (365 * 2) = 6.301...
    assertEquals(6.30, result, 0.01);
  }

  @Test
  void calculateAccruedInterest_withAct360() {
    double result =
        service.calculateAccruedInterest(
            1000.0, 0.05, LocalDate.of(2024, 6, 15), LocalDate.of(2024, 9, 15), "ACT/360");

    // 92 actual days, (1000 * 0.05 * 92) / (360 * 2) = 6.388...
    assertEquals(6.39, result, 0.01);
  }

  @Test
  void calculateAccruedInterest_with30_360() {
    double result =
        service.calculateAccruedInterest(
            1000.0, 0.05, LocalDate.of(2024, 6, 15), LocalDate.of(2024, 9, 15), "30/360");

    // 90 days (3 months * 30), (1000 * 0.05 * 90) / (360 * 2) = 6.25
    assertEquals(6.25, result, 0.01);
  }

  @Test
  void calculateAccruedInterest_withActAct_leapYear() {
    double result =
        service.calculateAccruedInterest(
            1000.0, 0.05, LocalDate.of(2024, 1, 1), LocalDate.of(2024, 4, 1), "ACT/ACT");

    // 2024 is leap year (366 days), 91 actual days
    // (1000 * 0.05 * 91) / (366 * 2) = 6.215...
    assertEquals(6.22, result, 0.01);
  }

  @Test
  void calculateAccruedInterest_throwsOnInvalidConvention() {
    assertThrows(
        IllegalArgumentException.class,
        () ->
            service.calculateAccruedInterest(
                1000.0, 0.05, LocalDate.of(2024, 6, 15), LocalDate.of(2024, 9, 15), "INVALID"));
  }
}
