package kr.inha.technical.college.press.manager.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "TB_NEWS_BOARD")
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
@ToString
@Transactional
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // GenerationType.IDENTITY : 기본키 생성을 데이터베이스에 위임
	@Column(name = "NEWS")
	private Long news;			
	
	@Column(name = "TITLE", nullable = false)
	private String title;
	
	@Column(name = "CONTENTS", nullable = false, length = 500000)
	private String contents;
	
	@Column(name = "MEMBER")
	private String member;
	
	@Column(name = "REGDATE")
	private LocalDateTime regdate;
	
	@Column(name = "VIEWCNT")
	private int viewcnt;
	
	@Column(name = "CATEGORY")
	private String category;
	
	@Column(name = "SUBCATEGORY")
	private String subcategory;
	
	@Column(name = "PHOTO")
	private String photo;
	
	@Column(name = "EMOJI")
	private int emoji;
}
