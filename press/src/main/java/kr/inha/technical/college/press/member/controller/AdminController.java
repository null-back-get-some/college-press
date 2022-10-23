package kr.inha.technical.college.press.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	
	@GetMapping(value = "/amdin/new")
	public String adminForm() {
		return "/manager/manager";
	}

}
