package org.ancloud.edge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AncloudEdgeApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(AncloudEdgeApplication.class);
        application.run(args);
	}
}
