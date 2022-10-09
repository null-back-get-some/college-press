package kr.inha.technical.college.press.calendar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.inha.technical.college.press.calendar.entity.entity;

public interface CalendarRepository extends JpaRepository<entity, Long>{
	List<entity> findById(long id);
	
}

