
package com.example.btlweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.btlweb.entity.User;

@SpringBootApplication
public class BtlwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(BtlwebApplication.class, args);
	}
	@Bean(name="USER_BEAN")
	public User setUser() {
		User user = new User();
		return user;
	}
}
