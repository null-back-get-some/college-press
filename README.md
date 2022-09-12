# college-press
학보사 홈페이지 리뉴얼

#### 09/13 프로젝트 생성
- kr/inha/technical/collge/press 로 프로젝트 생성
- dependency
  - mysql
  - devtools
  - spring web
  - lonbok 
  - thymeleaf
- application.properties → application.yml 로 변경
  -  디버그 콘솔 색바뀌는것만 세팅
  ```
  spring:
    output:
      ansi:
        enabled: always
  ```