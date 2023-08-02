package com.concrete.javachallenge.entity;

import java.util.List;
import lombok.Data;

@Data
public class Categories {
  // Category Three, Category
  private final String id;
  private final String name;
  private final int relevance;
  private final List<Categories> subcategories;
  // Category Lvl 2:
  private final String iconImageUrl;
  // Category Lvl 3:
  private final String largeImageUrl;
  private final String mediumImageUrl;
  private final String smallImageUrl;
}
