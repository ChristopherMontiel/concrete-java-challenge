package com.concrete.javachallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class JavachallengeApplication {

  public static void main(String[] args) {
    SpringApplication.run(JavachallengeApplication.class, args);
  }
}
