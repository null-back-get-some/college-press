package kr.inha.technical.college.press.manager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "TB_CATEGORY")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // GenerationType.IDENTITY : 기본키 생성을 데이터베이스에 위임
	@Column(name = "CATEGORY")
	private int category;
	
	
	@Column(name="CATEGORY_NAME")
    private String category_name;
}
