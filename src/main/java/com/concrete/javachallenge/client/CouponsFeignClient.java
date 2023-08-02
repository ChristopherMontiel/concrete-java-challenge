package com.concrete.javachallenge.client;

import com.concrete.javachallenge.configuration.feignClientConfiguration;
import com.concrete.javachallenge.entity.Coupons;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
    name = "coupons",
    url = "https://cs-hsa-api-coupons-rest.herokuapp.com",
    configuration = feignClientConfiguration.class)
public interface CouponsFeignClient {
  @GetMapping(value = "/coupons", consumes = MediaType.APPLICATION_JSON_VALUE)
  List<Coupons> couponsList();
}
