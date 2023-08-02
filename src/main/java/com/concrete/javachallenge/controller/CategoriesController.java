package com.concrete.javachallenge.controller;

import com.concrete.javachallenge.dto.CategoriesDTO;
import com.concrete.javachallenge.entity.Categories;
import com.concrete.javachallenge.exception.CategoriesNotFoundException;
import com.concrete.javachallenge.service.CategoriesService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoriesController {

  private final CategoriesService categoriesService;

  @GetMapping
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Success",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Categories.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
      })
  public ResponseEntity<?> getAllCategories() {
    try {
      Categories categories = categoriesService.getAllCategories();
      return new ResponseEntity<>(categories, HttpStatus.OK);
    } catch (CategoriesNotFoundException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/top")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Success",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CategoriesDTO.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
      })
  public ResponseEntity<?> getTopCategories() {
    try {
      List<CategoriesDTO> topCategories = categoriesService.getTopCategories();
      return new ResponseEntity<>(topCategories, HttpStatus.OK);
    } catch (CategoriesNotFoundException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/remaining")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Success",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CategoriesDTO.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
      })
  public ResponseEntity<?> getRemainingCategories() {
    try {
      List<CategoriesDTO> remainingCategories = categoriesService.getRemainingCategories();
      return new ResponseEntity<>(remainingCategories, HttpStatus.OK);
    } catch (CategoriesNotFoundException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
