package kr.inha.technical.college.press.calendar.entity;

import java.time.LocalDateTime;
import java.util.Date;

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
@Table(name = "TB_CALENDAR")
@Getter //필수로 만들어야 함
@Setter //안 만드는게 좋음. 외부에서 접근할 수 있기 때문에
@NoArgsConstructor //디폴트 생성자
@AllArgsConstructor //모든 생성자
@ToString
public class entity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // GenerationType.IDENTITY : 기본키 생성을 데이터베이스에 위임
	@Column(name = "ID")
	private Long id;			
	
	@Column(name = "WRITER")
	private String writer;
	
	@Column(name = "TITLE", nullable = false)
	private String title;
	
	@Column(name = "START", nullable = false)
	private LocalDateTime start;
	
	@Column(name = "END")
	private LocalDateTime end;
}
