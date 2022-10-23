package kr.inha.technical.college.press.manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kr.inha.technical.college.press.manager.entity.Category;
import kr.inha.technical.college.press.manager.entity.SubCategory;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

}
