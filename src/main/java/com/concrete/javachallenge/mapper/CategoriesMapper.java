package com.concrete.javachallenge.mapper;

import com.concrete.javachallenge.dto.CategoriesDTO;
import com.concrete.javachallenge.entity.Categories;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CategoriesMapper {

  public static List<CategoriesDTO> mapCategoriesToDTO(List<Categories> categories){
    return categories.stream().map(CategoriesMapper::mapCategoriesToDTO).toList();
  }

  private static CategoriesDTO mapCategoriesToDTO(Categories categories) {
    return new CategoriesDTO(
        categories.getId(),
        categories.getName(),
        categories.getRelevance(),
        categories.getIconImageUrl(),
        categories.getSmallImageUrl());
  }
}
