package kr.inha.technical.college.press.manager.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "TB_FILE")
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
@ToString
@Transactional
public class File {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // GenerationType.IDENTITY : 기본키 생성을 데이터베이스에 위임
	@Column(name = "F_ID")
	private Long id;			
	
	@ManyToOne
	@JoinColumn(name="NEWS")
	private Board board;	
	
	@Column(name="F_ORIGINAL_NAME")
	private String originalFileName;
	
	@Column(name="F_STORED_PATH")
	private String storedFilePath;
	
	@Column(name="F_FILESIZE")
	private long fielSize;
	
}
