
import java.io.IOException;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import redis.embedded.RedisServer;

@SpringBootConfiguration
public class EmbeddedRedis {
	private Logger logger = LoggerFactory.getLogger(EmbeddedRedis.class);
	public static void main(String[] args) {
		SpringApplication.run(EmbeddedRedis.class, args);
	}

	@Bean
	@Autowired
	CommandLineRunner init(final EmbeddedRedisEngine embeddedRedisEngine) {
		return new CommandLineRunner() {
			public void run(String... args) throws Exception {
				logger.info("Redis starting ...");
				embeddedRedisEngine.startRedis();
			}
		};

	}

	@Component
	public class EmbeddedRedisEngine {

		@Value("${spring.redis.port}")
		private int redisPort;

		private RedisServer redisServer;

		public void startRedis() throws IOException {
			redisServer = new RedisServer(redisPort);
			redisServer.start();
		}

		@PreDestroy
		public void stopRedis() {
			if(redisServer.isActive()){
				redisServer.stop();
			}
		}
	}
}
