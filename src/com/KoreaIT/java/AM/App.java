package com.KoreaIT.java.AM;

import com.KoreaIT.java.AM.dto.Article;
import com.KoreaIT.java.AM.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
  private List<Article> articles;

  App() {
    articles = new ArrayList<>();
  }

  public void start() {
    System.out.println("== 프로그램 시작 ==");
    makeTestData();
    Scanner sc = new Scanner(System.in);

    while (true) {
      System.out.printf("명령어 ) ");
      String cmd = sc.nextLine().trim();

      if (cmd.length() == 0) {
        System.out.println("명령어를 입력하세요");
        continue;
      }

      if (cmd.equals("system exit")) {
        break;
      }

      if (cmd.equals("article write")) {
        int id = articles.size() + 1;

        String regDate = Util.getNowDateStr();
        System.out.printf("제목 : ");
        String title = sc.nextLine();
        System.out.printf("내용 : ");
        String body = sc.nextLine();

        Article article = new Article(id, regDate, title, body);
        articles.add(article);

        System.out.printf("%d번 글이 생성 되었습니다.\n", id);

        // startsWitch 함수는 대상 문자열이 특정 문자 또는 문자열로 시작하는지 체크하는 함수이다.
      } else if (cmd.startsWith("article delete ")) {
        // split을 사용해 입력받은 문자열을 공백으로 구분해 cmdBits라는 배열에 저장한다.
        String[] cmdBits = cmd.split(" ");
        // id변수에 cmdBits배열의 2번째 위치에 있는 문자열을 int형으로 변환시켜서 넣어준다.
        int id = Integer.parseInt(cmdBits[2]);

        int foundIdx = getArticleIndexById(id);


        if (foundIdx == -1) {
          System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
          continue;
        }

        articles.remove(foundIdx);
        System.out.printf("%d번 글이 삭제 되었습니다.\n", id);

      } else if (cmd.startsWith("article modify ")) {
        String[] cmdBits = cmd.split(" ");
        int id = Integer.parseInt(cmdBits[2]);

        Article foundArticle = getArticleById(id);


        if (foundArticle == null) {
          System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
          continue;
        }

        System.out.printf("제목 : ");
        String title = sc.nextLine();
        System.out.printf("내용 : ");
        String body = sc.nextLine();

        foundArticle.title = title;
        foundArticle.body = body;

        System.out.printf("%d번 글이 수정 되었습니다.\n", id);

      } else if (cmd.startsWith("article detail ")) {
        String[] cmdBits = cmd.split(" ");
        int id = Integer.parseInt(cmdBits[2]);

        Article foundArticle = getArticleById(id);


        if (foundArticle == null) {
          System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
          continue;
        }
        foundArticle.increaseViewCnt();
        System.out.printf("번호 : %d\n", foundArticle.id);
        System.out.printf("날짜 : %s\n", foundArticle.regDate);
        System.out.printf("제목 : %s\n", foundArticle.title);
        System.out.printf("내용 : %s\n", foundArticle.body);
        System.out.printf("조회수 : %d\n", foundArticle.viewCnt);

      } else if (cmd.startsWith("article list")) {
        if (articles.size() == 0) {
          System.out.println("게시글이 없습니다.");
          continue;
        } else {
          String searchKeyword = cmd.substring("article list".length()).trim(); // 13개 짤라냄
//          System.out.printf("검색어 : %s\n", searchKeyword);

          List<Article> forPrintArticles = articles;
          if (searchKeyword.length() > 0) {
            forPrintArticles = new ArrayList<>(); // 새로운 창고 만듦

            for (Article article : articles) { // articles 순회
              if (article.title.contains(searchKeyword)) {
                forPrintArticles.add(article);
              }
            }
            if (forPrintArticles.size() == 0) {
              System.out.println("검색결과가 없습니다");
              continue;
            }
          }
          System.out.println("번호    |    제목    |    조회수");
          for (int i = forPrintArticles.size() - 1; i >= 0; i--) {
            Article article = forPrintArticles.get(i);
            System.out.printf("%4d    |    %2s    |    %3d\n", article.id, article.title, article.viewCnt);
          }
        }
      } else {
        System.out.println("존재하지 않는 명령어입니다.");
        continue;
      }
    }

    sc.close();
    System.out.println("== 프로그램 종료 ==");
  }

  private int getArticleIndexById(int id) {
    int i = 0;
    for (Article article : articles) {
      if (article.id == id) {
        return i;
      }
      i++;
    }
    return -1;
  }

  private Article getArticleById(int id) {
    // 발전과정1
//    for(int i = 0; i < articles.size(); i++) {
//      Article article = articles.get(i);
//
//      if (article.id == id) {
//        return article;
//      }
//    }
//    return null;


    // 발전과정2
//    for (Article article : articles) {
//      if (article.id == id) {
//        return article;
//      }
//    }

    // 발전과정3
    int index = getArticleIndexById(id);
    if (index != -1) {
      return articles.get(index);
    }
    return null;
  }

  private void makeTestData() {
    System.out.println("테스트데이터를 생성합니다");

    articles.add(new Article(1, Util.getNowDateStr(), "title1", "body1", 11));
    articles.add(new Article(2, Util.getNowDateStr(), "title2", "body2", 22));
    articles.add(new Article(3, Util.getNowDateStr(), "title3", "body3", 33));
  }
}