import org.ancloud.fs.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class,
									SecurityAutoConfiguration.class }, 
						scanBasePackages = { "org.ancloud.fs" })
public class Application extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	@Bean(name = "multipartResolver")
//	public CommonsMultipartResolver getResolver() throws IOException {
//		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//
//		// Set the maximum allowed size (in bytes) for each individual file.
//		resolver.setMaxUploadSizePerFile(1242880000);// 5MB
//
//		// You may also set other available properties.
//
//		return resolver;
//	}

	@Bean
	CommandLineRunner init(final StorageService storageService) {
		return new CommandLineRunner(){
			@Override
			public void run(String... args) throws Exception {
				//storageService.deleteAll();
				storageService.init();
			}};
		
	}
}
