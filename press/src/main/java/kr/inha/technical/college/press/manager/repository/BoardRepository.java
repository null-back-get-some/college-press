package kr.inha.technical.college.press.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.inha.technical.college.press.manager.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
