package kr.inha.technical.college.press.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import kr.inha.technical.college.press.board.dto.BoardSearchDto;
import kr.inha.technical.college.press.manager.entity.Board;

public interface BoardRepositoryCustom {
	
	Page<Board> getAdminItemPage(BoardSearchDto boardSearchDto, Pageable pageable);

}
