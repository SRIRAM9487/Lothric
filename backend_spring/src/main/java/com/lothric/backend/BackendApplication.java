package com.lothric.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** Lothric an file sharing application with real time messaging and inverntory mechanisms. */
@SpringBootApplication
public class BackendApplication {

  /** Main method. */
  public static void main(String[] args) {
    SpringApplication.run(BackendApplication.class, args);
  }
}
