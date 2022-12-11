package kr.inha.technical.college.press.manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.inha.technical.college.press.manager.entity.Board;
import kr.inha.technical.college.press.manager.repository.ManagerRepository;

@Service
public class ManagerService {
	@Autowired
	ManagerRepository repository;
	
	public void boardInsert(Board list) {
		 Board itemList = repository.save(list);
	}
	
	public List<Board> findAll() {
		List<Board> list =  repository.findAll();
		return list;
	}
	
	public void insertPhoto() {
		
	}

	public void boardDelete(Long id) {
		repository.deleteById(id);
	}
}
