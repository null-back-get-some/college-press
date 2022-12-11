package kr.inha.technical.college.press.board.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.inha.technical.college.press.board.dto.BoardSearchDto;
import kr.inha.technical.college.press.board.repository.BoardRepository;
import kr.inha.technical.college.press.manager.entity.Board;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
	@Autowired
	BoardRepository repository;
	
	@Autowired
	EntityManager em;
	
	public void saveBoard(Board board) {
		repository.save(board);
	}
	
	public List<Board> findAll() {
		List<Board> list =  repository.findAll();
		return list;
	}
	
	public Optional<Board> findByNews(Long news){
		Optional<Board> list = repository.findById(news);
		return list;
	}
	
	public Page<Board> findByCategoryAndSubcategory(int category, String subcategory, Pageable pageable) {
		return repository.findByCategoryAndSubcategory(category,subcategory, pageable);
	}
	
	//index 페이지 메인 기사 select(조회수 순으로)
	public Board findMaxBoard(){
		Board board = repository.findFirstByOrderByViewcntDesc();
		return board;
	}
	
	public List<Board> findArticleByViewCnt(int category){
		List<Board> board = repository.findFirst4ByCategoryOrderByViewcntDesc(category);
		return board;
	}
	
	@Transactional(readOnly = true)
	public Page<Board> getAdminItemPage(BoardSearchDto boardSearchDto, Pageable pageable) {
		return repository.getAdminItemPage(boardSearchDto, pageable);
	}
	
	
	
}
