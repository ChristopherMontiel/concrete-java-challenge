package com.concrete.javachallenge.service;

import static com.concrete.javachallenge.mapper.CategoriesMapper.mapCategoriesToDTO;

import com.concrete.javachallenge.client.CategoriesFeignClient;
import com.concrete.javachallenge.dto.CategoriesDTO;
import com.concrete.javachallenge.entity.Categories;
import com.concrete.javachallenge.exception.CategoriesNotFoundException;
import feign.FeignException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriesService {
  private final CategoriesFeignClient categoriesFeignClient;

  public Categories getAllCategories() {
    int maxRetries = 3;
    for (int retry = 0; retry < maxRetries; retry++) {
      try {
        Categories categories = categoriesFeignClient.categoriesList();
        System.out.println("Categorías obtenidas en el intento n° " + retry + 1);
        return categories;
      } catch (FeignException e) {
        System.out.println("Error al obtener las categorías: " + e.getMessage());
        try {
          Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
      }
    }
    throw new CategoriesNotFoundException(
        "No se pudieron obtener las categorías después de " + maxRetries + " reintentos.");
  }

  public List<CategoriesDTO> getTopCategories() {
    Categories allCategories = getAllCategories();
    if (allCategories != null) {
      return mapCategoriesToDTO(top5Categories(Collections.singletonList(allCategories)));
    } else {
      return null;
    }
  }

  public List<CategoriesDTO> getRemainingCategories() {
    Categories allCategories = getAllCategories();
    if (allCategories != null) {
      return mapCategoriesToDTO(remainingCategories(Collections.singletonList(allCategories)));
    } else {
      return null;
    }
  }

  private List<Categories> top5Categories(List<Categories> categoriesList) {
    List<Categories> flattenedCategories = flattenCategories(categoriesList);
    List<Categories> sortedCategories = orderCategoriesByRelevance(flattenedCategories);
    return sortedCategories.subList(0, Math.min(5, sortedCategories.size()));
  }

  private List<Categories> orderCategoriesByRelevance(List<Categories> flattenedCategories) {
    return flattenedCategories.stream()
        .sorted(Comparator.comparingInt(Categories::getRelevance).reversed())
        .collect(Collectors.toList());
  }

  private List<Categories> flattenCategories(List<Categories> categoriesList) {
    List<Categories> flattenedCategories = new ArrayList<>();
    for (Categories category : categoriesList) {
      flattenedCategories.add(category);
      if (category.getSubcategories() != null) {
        flattenedCategories.addAll(flattenCategories(category.getSubcategories()));
      }
    }
    return flattenedCategories;
  }

  private List<Categories> remainingCategories(List<Categories> categoriesList) {
    List<Categories> flattenedCategories = flattenCategories(categoriesList);
    List<Categories> sortedCategories = orderCategoriesByRelevance(flattenedCategories);
    int sizeList = sortedCategories.size();
    if (sizeList > 5) {
      return sortedCategories.subList(5, sizeList);
    }
    return null;
  }
}
