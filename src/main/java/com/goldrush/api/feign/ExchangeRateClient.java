package com.goldrush.api.feign;

import com.goldrush.api.dto.response.ExchangeRateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "exchange-rate-service", url = "${app.exchange-rate.url}")
public interface ExchangeRateClient {

    @GetMapping("/{apiKey}/pair/{fromCurrency}/{toCurrency}")
    ExchangeRateResponse getExchangeRate(@PathVariable String apiKey, @PathVariable String fromCurrency, @PathVariable String toCurrency);
}
