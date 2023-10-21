package com.thread.hello.boling_raman;

public class MonitorQueue {
  private final Object monitor = new Object();
  private boolean isResourceAvailable = true;

  public void useResource() {
    synchronized (monitor) {
      while (!isResourceAvailable) {
        try {
          monitor.wait();  // 대기 큐에 쓰레드 추가
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }

      isResourceAvailable = false;
      System.out.println(Thread.currentThread().getName() + " is using the resource.");

      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }

      isResourceAvailable = true;
      monitor.notifyAll();  // 대기 큐의 모든 쓰레드에게 알림
    }
  }

  public static void main(String[] args) {
    MonitorQueue monitorQueue = new MonitorQueue();
    for (int i = 0; i < 5; i++) {
      new Thread(monitorQueue::useResource).start();
    }
  }
}