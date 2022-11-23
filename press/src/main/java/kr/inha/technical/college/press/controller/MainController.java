package kr.inha.technical.college.press.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/main")
	public String index() {
		return "index";
	}
	
	@GetMapping("/pdftest")
	public String pdftest() {
		return "pdftest";
	}
	@GetMapping("/pdfjstest")
	public String pdfjstest() {
		return "pdfjstest";
	}
	
	@GetMapping("/viewer")
	public String viewer() {
		return "viewer";
	}
}
