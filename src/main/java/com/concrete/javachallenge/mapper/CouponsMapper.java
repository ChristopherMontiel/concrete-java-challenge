package com.concrete.javachallenge.mapper;

import com.concrete.javachallenge.dto.CouponsDTO;
import com.concrete.javachallenge.entity.Coupons;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CouponsMapper {
  public static List<CouponsDTO> mapCouponsToDTO(List<Coupons> coupons) {
    return coupons.stream().map(CouponsMapper::mapCouponsToDTO).toList();
  }

  private static CouponsDTO mapCouponsToDTO(Coupons coupons) {
    return new CouponsDTO(
        coupons.getId(),
        coupons.getDescription(),
        coupons.getSeller(),
        coupons.getImage(),
        coupons.getExpiresAt());
  }
}
