package kr.inha.technical.college.press.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	// 로그인 페이지
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	// 회원가입
	@GetMapping("/member/memberForm")
	public String memberForm() {
		return "member/memberForm";
	}
	
//	@GetMapping("/members/new")
//	public String members() {
//		return "members/new";
//	}

	// 메인 페이지
	@GetMapping("/index")
	public String index() {
		return "index";
	}

	// 관리자 게시판
	@GetMapping("/manager/manager")
	public String manager() {
		return "manager/manager";
	}

	@GetMapping("/tests")
	public String teset() {
		return "tests";
	}
	
}
