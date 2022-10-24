package kr.inha.technical.college.press.manager.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;

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
	
	@RequestMapping(value="/uploadSummernoteImageFile", produces = "application/json; charset=utf8")
	@ResponseBody
	public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request )  {
		
		System.out.println("===============>"+multipartFile.getOriginalFilename());
		return "";
	}
	
}
