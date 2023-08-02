package com.concrete.javachallenge.controller;

import com.concrete.javachallenge.dto.CouponsDTO;
import com.concrete.javachallenge.entity.Coupons;
import com.concrete.javachallenge.exception.CouponsNotFoundException;
import com.concrete.javachallenge.service.CouponsService;
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
@RequestMapping("/api/v1/coupons")
@RequiredArgsConstructor
public class CouponsController {
  private final CouponsService couponsService;

  @GetMapping
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Success",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Coupons.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
      })
  public ResponseEntity<?> getAllCoupons() {
    try {
      List<Coupons> coupons = couponsService.getAllCoupons();
      return new ResponseEntity<>(coupons, HttpStatus.OK);
    } catch (CouponsNotFoundException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/valid")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Success",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CouponsDTO.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
      })
  public ResponseEntity<?> getValidCoupons() {
    try {
      List<CouponsDTO> validCoupons = couponsService.getValidCoupons();
      return new ResponseEntity<>(validCoupons, HttpStatus.OK);
    } catch (CouponsNotFoundException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
