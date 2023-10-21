package com.thread.hello.boling_raman;

class currentThread extends Thread {
  public RamenCook ramenCook;
  static String nam;

  currentThread() {
    this(new RamenCook(5), "");
  }

  currentThread(RamenCook ramenCook, String nam) {
    this.ramenCook = ramenCook;
    this.nam = nam;
  }
}
