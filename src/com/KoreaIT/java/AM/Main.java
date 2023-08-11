package com.KoreaIT.java.AM;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    System.out.println("== 프로그램 시작 ==");
    Scanner sc = new Scanner(System.in); //sc -> 스캐너 객체와 연결된 변수


    System.out.printf("명령어 ) ");


//    String cmd = sc.nextLine(); // cmd변수에 입력한 내용 저장
    String cmd = sc.next(); // 넥스트는 맨 처음 어절만 저장, 넥스트라인은 한 줄 저장
//    cmd = sc.nextLine(); // 넥스트라인이 두번, 두번 입력
    System.out.printf("입력된 명령어 : %s\n", cmd);


    int num = sc.nextInt();
    System.out.printf("입력된 정수 : %d\n", num);













    sc.close();    //스캐너 닫아줌
    System.out.println("== 프로그램 종료 ==");
  }
}