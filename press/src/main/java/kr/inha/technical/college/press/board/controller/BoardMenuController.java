package kr.inha.technical.college.press.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import groovy.util.logging.Log4j2;
import groovyjarjarantlr4.v4.codegen.model.ModelElement;
import org.springframework.ui.Model;


@Controller
@Log4j2
@RequestMapping("/board")
public class BoardMenuController {

  // 보보 도대학 - 일반
	@GetMapping("/univ/normal")
	public String univNormal(Model model) {
    String title = "제목", content = "내용";

    model.addAttribute("t", title);
    model.addAttribute("c", content);
		return "board/univ/univ-normal";
	}

  // 보도대학 - 수업 교육
	@GetMapping("/univ/class")
	public String univClass(Model model) {
		String title = "제목", content = "내용";

    model.addAttribute("t", title);
    model.addAttribute("c", content);
		return "board/univ/univ-class";
	}

  // 보도대학 - 대학 행정
	@GetMapping("/univ/admin")
	public String univAdmin(Model model) {
		String title = "제목", content = "내용";

    model.addAttribute("t", title);
    model.addAttribute("c", content);
		return "board/univ/univ-admin";
	}


	  // 사회 - 일반
	@GetMapping("/social/normal")
	public String socialNormal(Model model) {
			String title = "제목", content = "내용";
	
			model.addAttribute("t", title);
			model.addAttribute("c", content);
			return "board/social/social-normal";
	}
	  // 사회 - 경제
	@GetMapping("/social/economy")
	public String socialEconomy(Model model) {
			String title = "제목", content = "내용";
	
			model.addAttribute("t", title);
			model.addAttribute("c", content);
			return "board/social/social-economy";
	}
	  // 사회 - 국제사회
	@GetMapping("/social/intl")
	public String socialIntl(Model model) {
			String title = "제목", content = "내용";
	
			model.addAttribute("t", title);
			model.addAttribute("c", content);
			return "board/social/social-intl";
	}



	// 종합문화 게시판 - 종합
	@GetMapping("/culture/total")
	public String cultureTotal(Model model) {
		String title = "제목", content = "내용";
	
		model.addAttribute("t", title);
		model.addAttribute("c", content);
		return "board/culture/culture-total";
	}
	// 종합문화 게시판 - 문화
	@GetMapping("/culture/cul")
	public String cultureCul(Model model) {
		String title = "제목", content = "내용";
	
		model.addAttribute("t", title);
		model.addAttribute("c", content);
		return "board/culture/culture-cul";
	}
	// 종합문화 게시판 - 오피니언
	@GetMapping("/culture/opinion")
	public String cultureOpinion(Model model) {
		String title = "제목", content = "내용";
	
		model.addAttribute("t", title);
		model.addAttribute("c", content);
		return "board/culture/culture-opinion";
	}



	// 사진/영상 게시판 - 행사
	@GetMapping("/picture/evnet")
	public String pictureEvnet(Model model) {
		String title = "제목";
		model.addAttribute("t", title);
		return "board/picture/picture-event";
	}
	// 사진/영상 게시판 - 보도
	@GetMapping("/picture/news")
	public String pictureNews(Model model) {
		String title = "제목";
		model.addAttribute("t", title);
		return "board/picture/picture-news";
	}
	// 사진/영상 게시판 - 영상
	@GetMapping("/picture/video")
	public String pictureVideo(Model model) {
		String title = "제목";
		model.addAttribute("t", title);
		return "board/picture/picture-video";
	}


	

	// 신문보기 게시판
	@GetMapping("/paper")
	public String paper() {
		return "board/paper";
	}
}
