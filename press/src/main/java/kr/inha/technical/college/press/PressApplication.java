package kr.inha.technical.college.press;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

@SpringBootApplication
public class PressApplication {

	public static void main(String[] args) {
		SpringApplication.run(PressApplication.class, args);
	}

	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}
}
