package com.thread.hello.boling_raman;

import static com.thread.hello.boling_raman.RamenProgram.log;

class RamenCook extends Thread implements Runnable {

  private int ramenCount;

  private String[] burners = {"_", "_", "_", "_"};

  public RamenCook(int count) {
    ramenCount = count;
  }

  @Override
  public void run() {
    while (ramenCount > 0) {
      System.out.println("ramenCount");
      synchronized (this) {
        ramenCount--;
        log.info(Thread.currentThread().getName() + " : " + ramenCount + "개 남았습니다");
      }

      for (int i = 0; i < burners.length; i++) {
        if (!burners[i].equals("_")) {
          continue;
        }

        synchronized (this) {
          burners[i] = Thread.currentThread().getName();
          log.info("                 " + Thread.currentThread().getName() + " : [" + (i + 1) + "]번 버너 ON");
          showBurners();
        }

        try {
          Thread.sleep(2000);
        } catch (Exception e) {
          e.printStackTrace();
        }

        synchronized (this) {
          burners[i] = "_";
          log.info("                                  " + Thread.currentThread().getName() + " : [" + (i + 1) + "]번 버너 OFF");
          showBurners();
        }
        break;
      }

      try {
        Thread.sleep(Math.round(1000 * Math.random()));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  private void showBurners() {
    String stringToPrint = "                                                             ";
    for (int i = 0; i < burners.length; i++) {
      stringToPrint += (" " + burners[i]);
    }
    log.info(stringToPrint);
  }
}
