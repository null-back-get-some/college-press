package kr.inha.technical.college.press.board.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import groovy.util.logging.Log4j2;
import kr.inha.technical.college.press.board.service.BoardService;
import kr.inha.technical.college.press.manager.entity.Board;
import kr.inha.technical.college.press.manager.entity.Category;
import kr.inha.technical.college.press.manager.entity.SubCategory;
import kr.inha.technical.college.press.manager.repository.CategoryRepository;
import kr.inha.technical.college.press.manager.repository.SubCategoryRepository;
import kr.inha.technical.college.press.manager.service.CategorySevice;

@Controller
@Log4j2
@RequestMapping("/board")
public class BoardMenuController {

	@Autowired
	BoardService boardService;

	@Autowired
	CategorySevice categorySevice;

	@Autowired
	SubCategoryRepository subCategoryRepository;

	List<Category> category;
	List<SubCategory> subCategories;

	// 보도대학 - 일반
	// category.get(0) = 보도대학(1)
	@GetMapping("/univ/normal")
	public String univNormal(Model model) {

		List<Board> board = loadPage(0, "일반");

		model.addAttribute("board", board);
		return "board/univ/univ-normal";
	}

	// 보도대학 - 수업 교육
	@GetMapping("/univ/class")
	public String univClass(Model model) {
		List<Board> board = loadPage(0, "수업 교육");

		model.addAttribute("board", board);
		return "board/univ/univ-class";
	}

	// 보도대학 - 대학 행정
	@GetMapping("/univ/admin")
	public String univAdmin(Model model) {
		List<Board> board = loadPage(0, "대학 행정");
		model.addAttribute("board", board);
		return "board/univ/univ-admin";
	}

	// 사회 - 일반 사회
	// category.get(1) = 사회(2)
	@GetMapping("/social/normal")
	public String socialNormal(Model model) {
		List<Board> board = loadPage(1, "일반 사회");
		model.addAttribute("board", board);
		return "board/social/social-normal";
	}

	// 사회 - 경제
	@GetMapping("/social/economy")
	public String socialEconomy(Model model) {
		List<Board> board = loadPage(1, "경제");
		model.addAttribute("board", board);
		return "board/social/social-economy";
	}

	// 사회 - 국제사회
	@GetMapping("/social/intl")
	public String socialIntl(Model model) {
		List<Board> board = loadPage(1, "국제 사회");
		model.addAttribute("board", board);
		return "board/social/social-intl";
	}

	// 종합문화 게시판 - 종합
	@GetMapping("/culture/total")
	public String cultureTotal(Model model) {
		List<Board> board = loadPage(2, "종합");
		model.addAttribute("board", board);
		return "board/culture/culture-total";
	}

	// 종합문화 게시판 - 문화
	@GetMapping("/culture/cul")
	public String cultureCul(Model model) {
		List<Board> board = loadPage(2, "문화");
		model.addAttribute("board", board);
		return "board/culture/culture-cul";
	}

	// 종합문화 게시판 - 오피니언
	@GetMapping("/culture/opinion")
	public String cultureOpinion(Model model) {
		List<Board> board = loadPage(2, "오피니언");
		model.addAttribute("board", board);
		return "board/culture/culture-opinion";
	}

	// 사진/영상 게시판 - 행사
	@GetMapping("/picture/evnet")
	public String pictureEvnet(Model model) {
		List<Board> board = loadPage(3, "행사");
		model.addAttribute("board", board);
		return "board/picture/picture-event";
	}

	// 사진/영상 게시판 - 보도
	@GetMapping("/picture/news")
	public String pictureNews(Model model) {
		List<Board> board = loadPage(3, "보도");
		model.addAttribute("board", board);
		return "board/picture/picture-news";
	}

	// 사진/영상 게시판 - 영상
	@GetMapping("/picture/video")
	public String pictureVideo(Model model) {
		List<Board> board = loadPage(3, "영상");
		model.addAttribute("board", board);
		return "board/picture/picture-video";
	}

	@GetMapping("/boardDetail")
	public String boardDetail(@RequestParam Long news, Model model) {

		// List<Board> board=boardService.findAll();
		Optional<Board> list = boardService.findByNews(news);
		Board board = list.get();

		System.out.println("boardDetail : " + board);

		model.addAttribute("board", board);
		return "board/boardDetail";
	}

	// 신문보기 게시판
	@GetMapping("/paper")
	public String paper() {
		return "board/paper";
	}

	//카테고리에 맞게 기사 로딩하는 메소드
	public List<Board> loadPage(int categories, String subcategory) {
		category = categorySevice.findAll();
		String cateNum = Integer.toString(category.get(categories).getCategory());
		List<Board> board = boardService.findByCategory(cateNum, subcategory);
		System.out.println(subcategory + " : " + board);
		return board;
	}
}
