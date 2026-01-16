package com.rates.server.runtime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.rates.server")
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
