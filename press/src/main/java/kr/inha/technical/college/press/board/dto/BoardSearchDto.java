package kr.inha.technical.college.press.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardSearchDto {

  private String title;
  private String content;
  
  // 검색 기능
  private String searchDateType;
  private String searchBy;
  private String searchQuery = "";
}
