package com.thread.hello.boling_raman;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;


public class RamenProgram {

  public static Logger log = LoggerFactory.getLogger(RamenCook.class);

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    log.info("라면 몇 개 끓일까요?");

    int num = input.nextInt();
    log.info(num + "개 주문 완료! 조리시작!");
    try {
      RamenCook ramenCook = new RamenCook(num);
      new Thread(ramenCook, "A").start();
      new Thread(ramenCook, "B").start();
      new Thread(ramenCook, "C").start();
      new Thread(ramenCook, "D").start();
    } catch (Exception exception) {
      log.error(exception.getMessage());
    }
  }
}



