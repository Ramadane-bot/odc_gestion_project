package com.odcspring.web_app;

import com.odcspring.web_app.model.User;
import com.odcspring.web_app.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@Controller
public class WebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebAppApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(UserService service){
		return args -> {
			User user = new User();
			user.setNom("Admin");
			user.setPassword("1234");
			user.setPrenom("Admin");
			user.setEmail("admin@odc.com");

			service.add(user);
		};
	}

}
