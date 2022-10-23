package kr.inha.technical.college.press.manager.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.inha.technical.college.press.calendar.entity.entity;
import kr.inha.technical.college.press.manager.dto.CategoryDto;
import kr.inha.technical.college.press.manager.entity.Board;
import kr.inha.technical.college.press.manager.entity.Category;
import kr.inha.technical.college.press.manager.entity.SubCategory;
import kr.inha.technical.college.press.manager.repository.CategoryRepository;
import kr.inha.technical.college.press.manager.repository.SubCategoryRepository;
import kr.inha.technical.college.press.manager.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	BoardService service;
	
	@Autowired
	CategoryRepository category;
	
	@Autowired
	SubCategoryRepository subCategory;
	
	// 게시판
	@GetMapping("/manager/board/board")
	public String board() {
		return "manager/board/board";
	}

	// 게시판
	@RequestMapping("/manager/boardWrite")
	public String boardWrite(Model model) {
		List<Category> mainCategory = category.findAll();
		List<SubCategory> sub_category = subCategory.findAll();
		
		model.addAttribute("mainCateList", mainCategory);
		model.addAttribute("subCateList", sub_category);
		System.out.println(mainCategory);
		System.out.println(sub_category);
		return "manager/boardWrite";
	}
	
	
	@GetMapping("/manager/pictureWrite")
	public String pictureWrite() {
		return "manager/pictureWrite";
	}
	
	@PostMapping("/manager/boardInsert")
	public String boardInsert(Board board, HttpServletRequest httpServletRequest) {
		String title = httpServletRequest.getParameter("main_category");
		System.out.println("===========================>"+board);
		service.boardInsert(board);
		return "redirect:/manager/manager";
	}
}
