package kr.inha.technical.college.press.manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.inha.technical.college.press.calendar.entity.entity;
import kr.inha.technical.college.press.manager.entity.Board;
import kr.inha.technical.college.press.manager.repository.BoardRepository;

@Service
public class BoardService {
	@Autowired
	BoardRepository repository;
	
	public void boardInsert(Board list) {
		 Board itemList = repository.save(list);
	}
	
	public List<Board> findAll() {
		List<Board> list =  repository.findAll();
		return list;
	}
}
