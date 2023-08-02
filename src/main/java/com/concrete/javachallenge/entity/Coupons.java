package com.concrete.javachallenge.entity;

import lombok.Data;

@Data
public class Coupons {
  private final String id;
  private final String description;
  private final String seller;
  private final String image;
  private final String expiresAt;
}
