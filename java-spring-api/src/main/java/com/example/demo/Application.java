package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class Application {

  @Value("${APP_MESSAGE:Hello from Java Spring!}")
  private String message;

  @GetMapping("/")
  public String hello() { return message; }

  @GetMapping("/health")
  public String health() { return "OK"; }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
