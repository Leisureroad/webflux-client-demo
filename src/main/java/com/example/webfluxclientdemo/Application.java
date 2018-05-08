package com.example.webfluxclientdemo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Date;

@Log
@SpringBootApplication
public class Application {

	@Bean
	WebClient webClient() {
		return WebClient.create("http://localhost:8080/cities");
	}

	@Bean
	CommandLineRunner demo(WebClient client) {
		return strings ->
				client.get()
						.uri("")
						.retrieve().bodyToFlux(City.class)
						.subscribe(city -> log.info(city.getName()+"-"+city.getCountry()));
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

@Data
@AllArgsConstructor
class City {
	private String name;
	private String country;
}