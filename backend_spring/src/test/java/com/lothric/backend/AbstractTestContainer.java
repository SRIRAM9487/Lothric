package com.lothric.backend;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

/** Test container. */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = BackendApplication.class)
public abstract class AbstractTestContainer {

  public static PostgreSQLContainer<?> postgresContainer =
      new PostgreSQLContainer<>("postgres:16")
          .withDatabaseName("chat_server")
          .withUsername("root")
          .withPassword("1234");

  static {
    postgresContainer.start();
  }

  @DynamicPropertySource
  static void databaseProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
    registry.add("spring.datasource.username", postgresContainer::getUsername);
    registry.add("spring.datasource.password", postgresContainer::getPassword);
  }
}
