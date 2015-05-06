//latch makes one or more threads waiting on the latch 
//All threads share a latch value. after each thread is done, it count downs the value of latch by 1
//One or more threads can countdown on the latch
//CountDownLatch is a thread safe class
//CountDownLatch lets you count down from a number specified, it lets one or more threads reach until latch reaches 0.
package com.thread.pool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Pro implements Runnable {
	private CountDownLatch latch;

	public Pro(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Thread started");
		// System.out.println("Latch value " + latch);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		latch.countDown();
		System.out.println("Current Latch value " + latch.getCount());

	}

}

public class Countdown {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		CountDownLatch latch = new CountDownLatch(3);
		ExecutorService executor = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 3; i++) {
			executor.submit(new Pro(latch));
		}
		try {
			latch.await(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			long end = System.currentTimeMillis();

			System.out.println("Completed");
			System.out.println("Time Taken :" + (end - start));
		}
	}

}
