package com.KoreaIT.java.AM;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    System.out.println("== 프로그램 시작 ==");
    Scanner sc = new Scanner(System.in); //sc -> 스캐너 객체와 연결된 변수


    int lastArticleId = 0;

    List<Article> articles = new ArrayList<>();


    while (true) {
      System.out.printf("명령어 ) ");
      String cmd = sc.nextLine().trim(); //trim -> 양옆공백자름

      if (cmd.length() == 0) {
        System.out.println("명령어를 입력하세요");
        continue;
      }


      if (cmd.equals("system exit")) {
        break;  //반복문 탈출해라
      }


      if (cmd.equals("article write")) {
        int id = lastArticleId + 1;
        lastArticleId = id;

        System.out.printf("제목 : ");
        String title = sc.nextLine();
        System.out.printf("내용 : ");
        String body = sc.nextLine();


        Article article = new Article(id, title, body);
        articles.add(article); // 리모컨 어레이리스트에 추가


        //       System.out.printf("%s, %s\n", title, body);
        System.out.printf("%d번 글이 생성 되었습니다\n", id);


      } else if (cmd.startsWith("article detail ")) {
        String[] cmdBits = cmd.split("  ");
//        cmdBits[0]; // article
//        cmdBits[1]; // detail
//        cmdBits[2]; // 게시글번호정보
        int id = Integer.parseInt(cmdBits[2]); //"2" -> 정수 2



        Article foundArticle = null;


        for (int i = 0; i < articles.size(); i++) { // 리스트 순회
          Article article = articles.get(i); //인덱스에서 꺼내옴
          if (article.id == id) {

            foundArticle = article; // 내용 제목 날짜 번호 article 리모컨
            break;
          }
        }
        if (foundArticle == null) {
          System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
          continue;
        }

        System.out.printf("번호 : %d\n", foundArticle.id);
        System.out.printf("날짜 : 2023-12-09 12:12:12\n");
        System.out.printf("제목 : %s\n", foundArticle.title);
        System.out.printf("내용 : %s\n", foundArticle.body);
      } else {
        for (int i = articles.size() - 1; i >= 0; i--) {
          Article article = articles.get(i);
          System.out.printf("%d, %s\n", article.id, article.title);
        }
        System.out.println("게시글이 존재");
      }


      else {
      System.out.println("존재하지 않는 명령어입니다");
      continue;

    }
  }




























    sc.close();    //스캐너 닫아줌
    System.out.println("== 프로그램 종료 ==");
  }
}


class Article {
  int id;
  String title;
  String body;

  Article(int id, String title, String body) {
    this.id = id;
    this.title = title;
    this.body = body;

  }
}