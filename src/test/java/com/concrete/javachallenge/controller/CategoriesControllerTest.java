package com.concrete.javachallenge.controller;

import com.concrete.javachallenge.client.CategoriesFeignClient;
import com.concrete.javachallenge.dto.CategoriesDTO;
import com.concrete.javachallenge.entity.Categories;
import com.concrete.javachallenge.service.CategoriesService;
import java.util.Collections;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CategoriesControllerTest {
  private CategoriesController categoriesController;

  @BeforeEach
  public void setUp() {
    categoriesController =
        new CategoriesController(
            new CategoriesService(
                new CategoriesFeignClient() {
                  @Override
                  public Categories categoriesList() {
                    return new Categories(
                        "id_test",
                        "name_test",
                        1,
                        null,
                        "iconImage_test",
                        "largeImage_test",
                        "mediumImage_test",
                        "smallImage_test");
                  }
                }));
  }

  @Test
  public void testTopCategories() {
    ResponseEntity<?> expected =
        new ResponseEntity<>(
            Collections.singletonList(
                new CategoriesDTO("id_test", "name_test", 1, "iconImage_test", "smallImage_test")),
            HttpStatus.OK);

    final ResponseEntity<?> result = categoriesController.getTopCategories();

    Assertions.assertEquals(expected, result);
  }
}
