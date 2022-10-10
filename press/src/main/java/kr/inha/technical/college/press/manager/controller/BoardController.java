package kr.inha.technical.college.press.manager.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.inha.technical.college.press.manager.entity.Board;
import kr.inha.technical.college.press.manager.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	BoardService service;
	
	// 게시판
	@GetMapping("/manager/board/board")
	public String board() {
		return "manager/board/board";
	}

	// 게시판
	@GetMapping("/manager/boardWrite")
	public String boardWrite() {
		return "manager/boardWrite";
	}
	
	@GetMapping("/manager/pictureWrite")
	public String pictureWrite() {
		return "manager/pictureWrite";
	}
	
	@PostMapping("/manager/boardInsert")
	public String boardInsert(Board board, HttpServletRequest httpServletRequest) {
		String title = httpServletRequest.getParameter("title");
		System.out.println("===========================>"+title);
		service.boardInsert(board);
		return "redirect:/manager/manager";
	}
}
