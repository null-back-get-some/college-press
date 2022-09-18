package kr.inha.technical.college.press;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"kr.inha.technical.college.press.controller"})
@SpringBootTest
class PressApplicationTests {

//	@Test
//	void contextLoads() {
//	}
	
	public static void main(String[] args) {
		SpringApplication.run(PressApplication.class, args);
	}

}
