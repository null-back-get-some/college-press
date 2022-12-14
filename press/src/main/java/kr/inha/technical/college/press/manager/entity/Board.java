package kr.inha.technical.college.press.manager.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
	
	@Column(name = "CONTENTS", nullable = false, length = 1000000000)
	private String contents;
	
	@Column(name = "MEMBER")
	private String member;
	
	@Column(name = "REGDATE")
	private String regdate;
	
	@Column(name = "VIEWCNT")
	private int viewcnt;
	
	@Column(name = "CATEGORY")
	private int category;
	
	@Column(name = "SUBCATEGORY")
	private String subcategory;
	
	@Column(name = "PHOTO", length = 100000000)
	private String photo;
	
	@Column(name="TEXT", length = 100000000)
	private String text;
	
	/*
	 * @Column(name = "EMOJI") private int emoji;
	 */
	
}
