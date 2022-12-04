package kr.inha.technical.college.press.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import kr.inha.technical.college.press.board.dto.BoardSearchDto;
import kr.inha.technical.college.press.manager.entity.Board;

public interface BoardRepositoryCustom extends JpaRepository<Board, Long>{
	
	Page<Board> findByTitle(BoardSearchDto boardSearchDto, Pageable pageable);

}
