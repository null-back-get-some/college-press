package kr.inha.technical.college.press.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/univ")
	public String univ() {
		return "univ";
	}
	
	@GetMapping("/social")
	public String social() {
		return "social";
	}
	
	@GetMapping("/culture")
	public String culture() {
		return "culture";
	}
	
	@GetMapping("/picture")
	public String picture() {
		return "picture";
	}
	
	@GetMapping("/paper")
	public String paper() {
		return "paper";
	}

	@GetMapping("/test")
	public String teset() {
		return "test";
	}
}
