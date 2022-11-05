package kr.inha.technical.college.press.board.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.inha.technical.college.press.board.repository.BoardRepository;
import kr.inha.technical.college.press.manager.entity.Board;
import kr.inha.technical.college.press.manager.entity.QBoard;

@Service
public class BoardService {
	@Autowired
	BoardRepository repository;
	
	@Autowired
	EntityManager em;
	
	public List<Board> findAll() {
		List<Board> list =  repository.findAll();
		return list;
	}
	
	public Optional<Board> findByNews(Long news){
		Optional<Board> list = repository.findById(news);
		return list;
	}
	
	public List<Board> findByCategory(String category, String subcategory){
		QBoard qBoard = QBoard.board;
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		List<Board> list=queryFactory.selectFrom(qBoard)
				.where(qBoard.category.like(category))
				.where(qBoard.subcategory.like(subcategory)).fetch();
		
		return list;
	}
	
}
