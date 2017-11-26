package org.ancloud.edge;

import org.ancloud.edge.AppConfig;
import org.springframework.boot.SpringApplication;

public class Application {

  public static void main(String[] args) {
    SpringApplication.run(AppConfig.class, args);
  }
}
