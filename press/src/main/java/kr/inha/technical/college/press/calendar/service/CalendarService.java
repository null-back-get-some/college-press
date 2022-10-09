package kr.inha.technical.college.press.calendar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.inha.technical.college.press.calendar.entity.entity;
import kr.inha.technical.college.press.calendar.repository.CalendarRepository;

@Service
public class CalendarService {
	@Autowired
	CalendarRepository repository;
	
	public void calendarInsert(entity list) {
		entity itemList = repository.save(list);
	}
	
	public List<entity> findAll() {
		List<entity> list =  repository.findAll();
		return list;
	}

	public void calendarDelete(Long id) {
		repository.deleteById(id);
	}
	
}