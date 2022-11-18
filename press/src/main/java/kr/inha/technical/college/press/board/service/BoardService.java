package kr.inha.technical.college.press.board.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	public List<Board> findByCategory(String category, String subcategory, Pageable pageable){
		QBoard qBoard = QBoard.board;
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		
		List<Board> list=queryFactory.selectFrom(qBoard)
				.where(qBoard.category.like(category))
				.where(qBoard.subcategory.like(subcategory))
				.offset(pageable.getOffset()) 
				.limit(pageable.getPageSize())
				.orderBy(qBoard.news.desc())
				.fetch();
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
	
}
