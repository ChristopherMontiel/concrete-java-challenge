package com.concrete.javachallenge.dto;

import com.concrete.javachallenge.entity.Categories;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class CategoriesDTO {
  private String id;
  private String name;
  private int relevance;
  // Category Lvl 2:
  private String iconImageUrl;
  // Category Lvl 3:
  private String smallImageUrl;
}
