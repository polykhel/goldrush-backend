package com.goldrush.api.service;

import com.goldrush.api.dto.response.ExchangeRateResponse;
import com.goldrush.api.feign.ExchangeRateClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ExchangeRateService {

  private final ExchangeRateClient exchangeRateClient;

  @Value("${app.exchange-rate.apiKey}")
  private String exchangeRateApiKey;

  public ExchangeRateService(ExchangeRateClient exchangeRateClient) {
    this.exchangeRateClient = exchangeRateClient;
  }

  @Cacheable(
      value = "exchangeRates",
      key = "#fromCurrency + '_' + #toCurrency",
      unless = "#result == null")
  public ExchangeRateResponse getExchangeRate(String fromCurrency, String toCurrency) {
    return exchangeRateClient.getExchangeRate(exchangeRateApiKey, fromCurrency, toCurrency);
  }
}
