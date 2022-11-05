package kr.inha.technical.college.press;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;

import kr.inha.technical.college.press.manager.entity.Board;

@ComponentScan(basePackages = {"kr.inha.technical.college.press.controller"})
@SpringBootTest
class PressApplicationTests {

//	@Test
//	void contextLoads() {
//	}
	
	/*
	 * @Autowired BoardRepository repository;
	 */
	
	public static void main(String[] args) {
		SpringApplication.run(PressApplication.class, args);
	}
	
	/*
	 * @Test
	 * 
	 * @DisplayName("게시판 저장 테스트") public void createItemTest() { Board item = new
	 * Board(); item.setTitle("1123"); item.setContents("홍길동"); Board savedItem =
	 * repository.save(item); // 저장 System.out.println(savedItem.toString()); }
	 */

}
