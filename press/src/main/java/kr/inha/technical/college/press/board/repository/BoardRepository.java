package kr.inha.technical.college.press.board.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import kr.inha.technical.college.press.manager.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long>, 
QuerydslPredicateExecutor<Board>{ // Board 엔티티
	
	Page<Board> findByCategoryAndSubcategory(int category, String subcategory, Pageable pageable);
}
