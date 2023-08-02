package com.concrete.javachallenge.controller;

import com.concrete.javachallenge.client.CouponsFeignClient;
import com.concrete.javachallenge.dto.CouponsDTO;
import com.concrete.javachallenge.entity.Coupons;
import com.concrete.javachallenge.service.CouponsService;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CouponsControllerTest {
  private CouponsController couponsController;

  @BeforeEach
  public void setUp() {
    couponsController =
        new CouponsController(
            new CouponsService(
                new CouponsFeignClient() {
                  @Override
                  public List<Coupons> couponsList() {
                    return Collections.singletonList(
                        new Coupons(
                            "id_test",
                            "description_test",
                            "seller_test",
                            "image_test",
                            "2045-12-01"));
                  }
                }));
  }

  @Test
  public void testValidCoupons() {
    ResponseEntity<?> expected =
        new ResponseEntity<>(
            Collections.singletonList(
                new CouponsDTO(
                    "id_test", "description_test", "seller_test", "image_test", "2045-12-01")),
            HttpStatus.OK);

    final ResponseEntity<?> result = couponsController.getValidCoupons();

    Assertions.assertEquals(expected, result);
  }
}
