package com.matribio.matribio_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// import com.matribio.matribio_service.dto.SimpleMessage;
// import com.matribio.matribio_service.entity.User;
import com.matribio.matribio_service.service.UserService;

@SpringBootApplication
public class MatribioServiceApplication implements CommandLineRunner {

	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(MatribioServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// SimpleMessage signup = userService.userSignup(new User(null, "moni", "moni12", "moni@gmail.com", "ROLE_USER,ROLE_ADMIN", null, "self_platform"));
		// System.out.println("Test user signup :: "+ signup.message());

		// userService.userSignup(new User(null, "user", "user12", "user@gmail.com", "ROLE_USER", null, "self_platform"));
		// userService.userSignup(new User(null, "admin", "admin12", "admin@gmail.com", "ROLE_ADMIN", null, "self_platform"));

	}

}
