package com.concrete.javachallenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CouponsDTO {
  private String id;
  private String description;
  private String seller;
  private String image;
  private String expiresAt;
}
