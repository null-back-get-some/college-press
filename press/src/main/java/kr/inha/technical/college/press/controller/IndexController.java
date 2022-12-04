package kr.inha.technical.college.press.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.inha.technical.college.press.board.repository.BoardRepository;
import kr.inha.technical.college.press.board.service.BoardService;
import kr.inha.technical.college.press.manager.entity.Board;

@Controller
public class IndexController {

	@Autowired
	BoardService service;
	
	@Autowired
	BoardRepository repository;
	
	// 로그인 페이지
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/test")
	public String test() {

		return "/test";
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
	@GetMapping(value = {"/", "/index"})
	public String index(Model model) {
		System.out.println("index 접근");
		//매개변수 : 카테고리
		List<Board> univ = service.findArticleByViewCnt(1); 	//보도대학
		List<Board> social = service.findArticleByViewCnt(2); 	//사회
		List<Board> culture = service.findArticleByViewCnt(3);	//종합문화
		Board board = service.findMaxBoard();
		System.out.println("=========>"+board.getTitle()+", "+board.getViewcnt());
		
		for (Board board2 : univ) {
			System.out.println(board2.getTitle()+", "+board2.getViewcnt()+", "+board2.getCategory()+", "+board2.getSubcategory());
		}
		model.addAttribute("univ", univ);
		model.addAttribute("social", social);
		model.addAttribute("culture", culture);
		model.addAttribute("mainArticle", board);
		return "index";
	}

}
