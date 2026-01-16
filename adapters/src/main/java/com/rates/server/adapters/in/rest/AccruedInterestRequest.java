package com.rates.server.adapters.in.rest;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

public record AccruedInterestRequest(
    @NotNull @Positive double faceValue,
    @NotNull @Positive double couponRate,
    @NotNull LocalDate lastCouponDate,
    @NotNull LocalDate settlementDate,
    @NotNull String dayCountConvention) {}
