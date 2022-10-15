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
	
	// 로그인 페이지
	@GetMapping("/univ2")
	public String index2() {
		return "univ2";
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

	// 보도대학 게시판
	@GetMapping("/univ")
	public String univ() {
		return "univ";
	}

	// 사회 게시판
	@GetMapping("/social")
	public String social() {
		return "social";
	}

	// 종합문화 게시판
	@GetMapping("/culture")
	public String culture() {
		return "culture";
	}

	// 사진/영상 게시판
	@GetMapping("/picture")
	public String picture() {
		return "picture";
	}

	// 신문보기 게시판
	@GetMapping("/paper")
	public String paper() {
		return "paper";
	}
	// 신문보기 게시판
	@GetMapping("/paper2")
	public String paper2() {
		return "paper2";
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
