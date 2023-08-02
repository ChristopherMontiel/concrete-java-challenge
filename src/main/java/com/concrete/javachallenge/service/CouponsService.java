package com.concrete.javachallenge.service;

import com.concrete.javachallenge.client.CouponsFeignClient;
import com.concrete.javachallenge.dto.CouponsDTO;
import com.concrete.javachallenge.entity.Coupons;
import com.concrete.javachallenge.exception.CouponsNotFoundException;
import feign.FeignException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.concrete.javachallenge.mapper.CategoriesMapper.mapCategoriesToDTO;
import static com.concrete.javachallenge.mapper.CouponsMapper.mapCouponsToDTO;

@Service
@RequiredArgsConstructor
public class CouponsService {
  private final CouponsFeignClient couponsFeignClient;

  public List<Coupons> getAllCoupons() {
    int maxRetries = 3;
    for (int retry = 0; retry < maxRetries; retry++) {
      try {
        List<Coupons> coupons = couponsFeignClient.couponsList();
        System.out.println("Cupones obtenidos en el intento n° " + retry + 1);
        return coupons;
      } catch (FeignException e) {
        System.out.println("Error al obtener los cupones: " + e.getMessage());
        try {
          Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
      }
    }
    throw new CouponsNotFoundException(
        "No se pudieron obtener los cupones después de " + maxRetries + " reintentos.");
  }

  public List<CouponsDTO> getValidCoupons() {
    List<Coupons> allCoupons = getAllCoupons();
    if (allCoupons != null) {
      LocalDate currentDate = LocalDate.now();
      return mapCouponsToDTO(allCoupons.stream().filter(coupon -> isCouponValid(coupon, currentDate)).toList());
    } else {
      return null;
    }
  }

  private boolean isCouponValid(Coupons coupon, LocalDate currentDate) {
    LocalDate expirationDate = LocalDate.parse(coupon.getExpiresAt(), DateTimeFormatter.ISO_DATE);
    return expirationDate.isAfter(currentDate) || expirationDate.isEqual(currentDate);
  }
}
