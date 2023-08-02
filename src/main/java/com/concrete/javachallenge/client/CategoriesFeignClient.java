package com.concrete.javachallenge.client;

import com.concrete.javachallenge.entity.Categories;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "categories", url = "https://cs-hsa-api-categories-rest.herokuapp.com")
public interface CategoriesFeignClient {
  @GetMapping(value = "/categories", consumes = MediaType.APPLICATION_JSON_VALUE)
  Categories categoriesList();
}
