import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootConfiguration
@ComponentScan(basePackages = { "org.ancloud" })
@EnableRedisHttpSession
@EnableAutoConfiguration
//@SpringBootApplication
@RestController

public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@RequestMapping("/")
	public String hello(HttpSession session) {
		UUID uid = (UUID) session.getAttribute("uid");
		if (uid == null) {
			uid = UUID.randomUUID();
		}
		session.setAttribute("uid", uid);
		return uid.toString();
	}
}
