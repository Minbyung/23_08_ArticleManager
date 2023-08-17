package com.KoreaIT.java.AM.dto;

// Article 객체를 사용하기 위해 Article 클래스를 생성
// id, title, body라는 변수를 생성
// regDate라는 변수도 Article 클래스에 추가해준다.
public class Article {
  public int id;
  public String regDate;
  public String title;
  public String body;
  public int viewCnt;

  // 매개변수를 사용해 Article에 3개의 변수가 들어가게 해준다.
  // this를 사용해 위에 있는 변수를 사용
  public Article(int id, String regDate, String title, String body) {
    this(id, regDate, title, body, 0);
  }

  public Article(int id, String regDate, String title, String body, int viewCnt) {
    this.id = id;
    this.regDate = regDate;
    this.title = title;
    this.body = body;
    this.viewCnt = viewCnt;
  }

  public void increaseViewCnt() {
    viewCnt++;
  }
}