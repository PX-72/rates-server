package com.rates.server.runtime.config;

import com.rates.server.domain.services.BondAnalyticsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

  @Bean
  public BondAnalyticsService bondAnalyticsService() {
    return new BondAnalyticsService();
  }
}
