package com.goldrush.api.web;

import com.goldrush.api.dto.response.ExchangeRateResponse;
import com.goldrush.api.service.ExchangeRateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exchange-rate")
public class ExchangeRateResource {

    private final ExchangeRateService exchangeRateService;

    public ExchangeRateResource(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping("/from/{fromCurrency}/to/{toCurrency}")
    public ExchangeRateResponse getExchangeRate(@PathVariable String fromCurrency, @PathVariable String toCurrency) {
        return exchangeRateService.getExchangeRate(fromCurrency, toCurrency);
    }
}
