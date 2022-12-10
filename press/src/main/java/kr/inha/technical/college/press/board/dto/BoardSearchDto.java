package kr.inha.technical.college.press.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardSearchDto {

  private String title;
  private String content;
  
  // 검색 기능
  private String searchBy;
  private String searchQuery = "";
}
