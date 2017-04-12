package org.ancloud.edge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class AncloudEdgeApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(AncloudEdgeApplication.class);
        application.run(args);
	}
}
