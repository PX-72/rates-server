package com.rates.server.adapters.in.rest;

public record AccruedInterestResponse(
    double faceValue,
    double couponRate,
    String lastCouponDate,
    String settlementDate,
    String dayCountConvention,
    double accruedInterest) {}
