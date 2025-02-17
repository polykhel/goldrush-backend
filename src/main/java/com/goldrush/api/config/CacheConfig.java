package com.goldrush.api.config;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import javax.cache.Caching;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.core.config.DefaultConfiguration;
import org.ehcache.expiry.ExpiryPolicy;
import org.ehcache.jsr107.EhcacheCachingProvider;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {

  @Bean
  public CacheManager jCacheCacheManager() {
    Map<String, CacheConfiguration<?, ?>> cacheMap = new HashMap<>();

    ResourcePoolsBuilder resourcePoolsBuilder = ResourcePoolsBuilder.heap(3);

    CacheConfiguration<String, Object> cacheConfiguration =
        CacheConfigurationBuilder.newCacheConfigurationBuilder(
                String.class, Object.class, resourcePoolsBuilder)
            .withExpiry(new MidnightExpiryPolicy())
            .build();

    cacheMap.put("exchangeRates", cacheConfiguration);
    EhcacheCachingProvider ehcacheCachingProvider =
        (EhcacheCachingProvider) Caching.getCachingProvider(EhcacheCachingProvider.class.getName());
    DefaultConfiguration defaultConfiguration =
        new DefaultConfiguration(cacheMap, ehcacheCachingProvider.getDefaultClassLoader());
    javax.cache.CacheManager cacheManager =
        ehcacheCachingProvider.getCacheManager(
            ehcacheCachingProvider.getDefaultURI(), defaultConfiguration);
    return new JCacheCacheManager(cacheManager);
  }

  private static class MidnightExpiryPolicy implements ExpiryPolicy<String, Object> {

    @Override
    public Duration getExpiryForCreation(String key, Object value) {
      // Calculate time remaining until midnight
      LocalDateTime now = LocalDateTime.now();
      LocalDateTime midnight = now.toLocalDate().atStartOfDay().plusDays(1);
      long secondsUntilMidnight = java.time.Duration.between(now, midnight).getSeconds();
      return Duration.ofSeconds(secondsUntilMidnight); // Entry TTL until midnight
    }

    @Override
    public Duration getExpiryForAccess(String s, Supplier<?> supplier) {
      return null;
    }

    @Override
    public Duration getExpiryForUpdate(String s, Supplier<?> supplier, Object o) {
      return null;
    }
  }
}
