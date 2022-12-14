package kr.inha.technical.college.press.board.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;

import groovy.util.logging.Log4j2;
import kr.inha.technical.college.press.board.dto.BoardSearchDto;
import kr.inha.technical.college.press.board.service.BoardService;
import kr.inha.technical.college.press.manager.entity.Board;
import kr.inha.technical.college.press.manager.entity.Category;
import kr.inha.technical.college.press.manager.entity.FileEntity;
import kr.inha.technical.college.press.manager.entity.SubCategory;
import kr.inha.technical.college.press.manager.repository.SubCategoryRepository;
import kr.inha.technical.college.press.manager.service.CategorySevice;
import kr.inha.technical.college.press.manager.service.FileService;
import kr.inha.technical.college.press.member.entity.Member;
import kr.inha.technical.college.press.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Log4j2
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardMenuController {

	@Autowired
	BoardService boardService;

	@Autowired
	CategorySevice categorySevice;
	
	@Autowired
	FileService fileService;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	SubCategoryRepository subCategoryRepository;

	List<Category> category;
	List<SubCategory> subCategories;

	ResourceLoader resourceLoader;

    @Autowired
    public BoardMenuController (ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
	
	// ???????????? - ??????
	// category.get(0) = ????????????(1)
	@GetMapping("/univ/normal")
	public String univNormal(Model model, @PageableDefault(size=10,sort="news", direction = Sort.Direction.DESC)Pageable pageable) {
		//List<Board> board = loadPage(0, "??????", pageable);
		Page<Board> board = loadPage(1, "??????", pageable);
        
		int nowPage =  board.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 5, board.getTotalPages());
		
		List<Board> ManySee = boardService.findArticleByViewCnt(1); //???????????? ?????? ??? ??????
		System.out.println("photo : "+board.getContent().get(0).getPhoto());
		model.addAttribute("manySee", ManySee);		//?????? ??? ??????
		model.addAttribute("board", board);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
		return "board/univ/univ-normal";
	}

	// ???????????? - ?????? ??????
	@GetMapping("/univ/class")
	public String univClass(Model model, @PageableDefault(size=10,sort="news", direction = Sort.Direction.DESC)Pageable pageable) {
		Page<Board> board = loadPage(1, "?????? ??????", pageable);
		int nowPage =  board.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 5, board.getTotalPages());
		
		List<Board> ManySee = boardService.findArticleByViewCnt(1);
		model.addAttribute("manySee", ManySee);	
		model.addAttribute("board", board);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
		return "board/univ/univ-class";
	}

	// ???????????? - ?????? ??????
	@GetMapping("/univ/admin")
	public String univAdmin(Model model, @PageableDefault(size=10,sort="news", direction = Sort.Direction.DESC)Pageable pageable) {
		Page<Board> board = loadPage(1, "?????? ??????",pageable);
		int nowPage =  board.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 5, board.getTotalPages());
		
		List<Board> ManySee = boardService.findArticleByViewCnt(1);
		model.addAttribute("manySee", ManySee);	
		model.addAttribute("board", board);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
		return "board/univ/univ-admin";
	}

	// ?????? - ?????? ??????
	// category.get(1) = ??????(2)
	@GetMapping("/social/normal")
	public String socialNormal(Model model, @PageableDefault(size=10,sort="news", direction = Sort.Direction.DESC)Pageable pageable) {
		Page<Board> board = loadPage(2, "?????? ??????",pageable);

		int nowPage =  board.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 5, board.getTotalPages());
		
		List<Board> ManySee = boardService.findArticleByViewCnt(2);
		model.addAttribute("manySee", ManySee);	
		model.addAttribute("board", board);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
		return "board/social/social-normal";
	}

	// ?????? - ??????
	@GetMapping("/social/economy")
	public String socialEconomy(Model model,  @PageableDefault(size=10,sort="news", direction = Sort.Direction.DESC)Pageable pageable) {
		Page<Board> board = loadPage(2, "??????",pageable);

		int nowPage =  board.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 5, board.getTotalPages());

		List<Board> ManySee = boardService.findArticleByViewCnt(2);
		model.addAttribute("manySee", ManySee);	
		model.addAttribute("board", board);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
		return "board/social/social-economy";
	}

	// ?????? - ????????????
	@GetMapping("/social/intl")
	public String socialIntl(Model model, @PageableDefault(size=10,sort="news", direction = Sort.Direction.DESC)Pageable pageable) {
		Page<Board> board = loadPage(2, "?????? ??????",pageable);

		int nowPage =  board.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 5, board.getTotalPages());
		
		List<Board> ManySee = boardService.findArticleByViewCnt(2);
		model.addAttribute("manySee", ManySee);	
		model.addAttribute("board", board);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
		return "board/social/social-intl";
	}

	// ???????????? ????????? - ??????
	@GetMapping("/culture/total")
	public String cultureTotal(Model model, @PageableDefault(size=10,sort="news", direction = Sort.Direction.DESC)Pageable pageable) {
		Page<Board> board = loadPage(3, "??????",pageable);

		int nowPage =  board.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 5, board.getTotalPages());
		
		List<Board> ManySee = boardService.findArticleByViewCnt(3);
		model.addAttribute("manySee", ManySee);	
		model.addAttribute("board", board);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
		return "board/culture/culture-total";
	}

	// ???????????? ????????? - ??????
	@GetMapping("/culture/cul")
	public String cultureCul(Model model, @PageableDefault(size=10,sort="news", direction = Sort.Direction.DESC)Pageable pageable) {
		Page<Board> board = loadPage(3, "??????",pageable);

		int nowPage =  board.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 5, board.getTotalPages());
		
		List<Board> ManySee = boardService.findArticleByViewCnt(3);
		model.addAttribute("manySee", ManySee);	
		model.addAttribute("board", board);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
		return "board/culture/culture-cul";
	}

	// ???????????? ????????? - ????????????
	@GetMapping("/culture/opinion")
	public String cultureOpinion(Model model, @PageableDefault(size=10,sort="news", direction = Sort.Direction.DESC)Pageable pageable) {
		Page<Board> board = loadPage(3, "????????????",pageable);

		int nowPage =  board.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 5, board.getTotalPages());
		
		List<Board> ManySee = boardService.findArticleByViewCnt(3);
		model.addAttribute("manySee", ManySee);	
		model.addAttribute("board", board);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
		return "board/culture/culture-opinion";
	}

	// ??????/?????? ????????? - ??????
	@GetMapping("/picture/event")
	public String pictureEvnet(Model model, @PageableDefault(size=10,sort="news", direction = Sort.Direction.DESC)Pageable pageable) {
		Page<Board> board = loadPage(4, "??????",pageable);

		int nowPage =  board.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 5, board.getTotalPages());
		
		model.addAttribute("board", board);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
		return "board/picture/picture-event";
	}

	// ??????/?????? ????????? - ??????
	@GetMapping("/picture/news")
	public String pictureNews(Model model, @PageableDefault(size=10,sort="news", direction = Sort.Direction.DESC)Pageable pageable) {
		Page<Board> board = loadPage(4, "??????",pageable);

		int nowPage =  board.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 5, board.getTotalPages());
		
		model.addAttribute("board", board);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
		return "board/picture/picture-news";
	}

	// ??????/?????? ????????? - ??????
	@GetMapping("/picture/video")
	public String pictureVideo(Model model, @PageableDefault(size=10,sort="news", direction = Sort.Direction.DESC)Pageable pageable) {
		Page<Board> board = loadPage(4, "??????",pageable);

		int nowPage =  board.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 5, board.getTotalPages());
		
		model.addAttribute("board", board);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
		return "board/picture/picture-video";
	}

	@GetMapping("/boardDetail")
	public String boardDetail(@RequestParam Long news, Model model) {
		// List<Board> board=boardService.findAll();
		Optional<Board> list = boardService.findByNews(news);
		Board board = list.get();
		board.setViewcnt(board.getViewcnt()+1);
		boardService.saveBoard(board);
		System.out.println("boardDetail : " + board);
		String email = memberService.findEmail(board.getMember());
		
		System.out.println(">>>>>>>>>>>myemail : "+email);
		model.addAttribute("email", email);
		model.addAttribute("board", board);
		return "board/boardDetail";
	}
	
	@GetMapping("/boardModify")
	public String boardModify(@RequestParam Long news, Model model, Principal principal) {
		// List<Board> board=boardService.findAll();
		Optional<Board> list = boardService.findByNews(news);
		Board board = list.get();
		
		String username = memberService.findByEmail(principal.getName()).getName();

		List<Category> mainCategory = categorySevice.findAll();
		List<SubCategory> sub_category = subCategoryRepository.findAll();

		model.addAttribute("username", username);
		model.addAttribute("mainCateList", mainCategory);
		model.addAttribute("subCateList", sub_category);
		System.out.println(mainCategory);
		System.out.println(sub_category);
		
		
		model.addAttribute("board", board);
		return "board/boardModify";
	}
	
	
	
	@GetMapping("/pictureDetail")
	public String pictureDetail(@RequestParam Long news, Model model) {

		// List<Board> board=boardService.findAll();
		Optional<Board> list = boardService.findByNews(news);
		Board board = list.get();
		board.setViewcnt(board.getViewcnt()+1);
		boardService.saveBoard(board);
		System.out.println("boardDetail : " + board);

		model.addAttribute("board", board);
		return "board/pictureDetail";
	}
	
	// ???????????? ?????????
	@GetMapping("paper/paper")
	public String paper(Model model) {
		List<FileEntity> board = fileService.findAll();
		System.out.println("==========??? : "+board.get(0).getOriginalFileName());
		model.addAttribute("board", board);
		return "board/paper/paper";
	}
	
	@GetMapping("/paperDetail")
	public String paperDetail(@RequestParam Long id, Model model) {
		Optional<FileEntity> list = fileService.findbyId(id);
		FileEntity board = list.get();
		
		model.addAttribute("board", board);
		return "board/paperDetail";
	}
	

    @RequestMapping("paper/download")
	public String downloadBoardFile(@RequestParam Long idx, //?????? ????????? 
			HttpServletResponse response) throws Exception {
			
			Optional<FileEntity> boardFile = fileService.findbyId(idx);
		
			if(ObjectUtils.isEmpty(boardFile)==false) {
				String fileName = boardFile.get().getOriginalFileName();
				byte[] files = FileUtils.readFileToByteArray(new File(boardFile.get().getSavedPath()));
			
			//response ????????? ??????
			response.setContentType("application/octet-stream");
			response.setContentLength(files.length);
			response.setHeader("Content-Disposition", 
					"attachment; filename=\"" +URLEncoder.encode(fileName,"UTF-8")+"\";");
			response.setHeader("Content-Transfer-Encoing", "binary");
			
			response.getOutputStream().write(files);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}
		
		
		return "redirect:/";
	}
    
    
    
	
	// ?????? ?????????
	@GetMapping({"/searchDetail", "/searchDetail/{page}"})
	public String itemPageList(BoardSearchDto boardSearchDto, Model model, @PathVariable("page") Optional<Integer> page) {
				
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 10);
		Page<Board> items = boardService.getAdminItemPage(boardSearchDto, pageable);
		
		log.info("??????: " + items.getContent().size());
		log.info("number: " + items.getNumber());
		log.info("pages: " + items.getTotalPages());
		
		model.addAttribute("items", items);
		model.addAttribute("boardSearchDto", boardSearchDto);
		model.addAttribute("maxPage", 5);
		
		return "board/searchDetail";
		
	}
	
	
	
	//??????????????? ?????? ?????? ???????????? ?????????
	public Page<Board> loadPage(int categories, String subcategory, Pageable pageable) {
		Page<Board> board = boardService.findByCategoryAndSubcategory(categories, subcategory, pageable);
        
		System.out.println(subcategory + " : " + board);
		return board;
	}
	
}
