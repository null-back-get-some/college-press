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

	// 보도대학 게시판
	@GetMapping("/board/univ")
	public String univ() {
		return "board/univ";
	}

	// 사회 게시판
	@GetMapping("/board/social")
	public String social() {
		return "board/social";
	}

	// 종합문화 게시판
	@GetMapping("/board/culture")
	public String culture() {
		return "board/culture";
	}

	// 사진/영상 게시판
	@GetMapping("/board/picture")
	public String picture() {
		return "board/picture";
	}

	// 신문보기 게시판
	@GetMapping("/board/paper")
	public String paper() {
		return "board/paper";
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
