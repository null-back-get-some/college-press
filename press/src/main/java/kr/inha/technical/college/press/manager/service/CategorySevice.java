package kr.inha.technical.college.press.manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.inha.technical.college.press.manager.entity.Category;
import kr.inha.technical.college.press.manager.repository.CategoryRepository;

@Service
public class CategorySevice {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	public List<Category> findAll() {
		List<Category> list = categoryRepository.findAll();
		return list;
	}
}
