package com.reentrant.lock;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
	private int count = 0;
	private Lock lock = new ReentrantLock();
	private Condition cond = lock.newCondition();

	public void increment() {

		for (int i = 0; i < 10000; i++) {
			count++;
		}
	}

	public void firstThread() {
		// TODO Auto-generated method stub
		lock.lock();
		try {
			cond.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			System.out.println("From first thread");
			increment();
		} finally {
			lock.unlock();

		}
	}

	public void secondThread() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lock.lock();
		System.out.println("Press the return key!");
		new Scanner(System.in).nextLine();
		System.out.println("Got return key!");
		cond.signal();
		try {
			System.out.println("From second thread");
			increment();
		} finally {
			lock.unlock();

		}

	}

	public void finished() {
		// TODO Auto-generated method stub
		System.out.println("Everything is done ");
		System.out.println("Count is " + count);
		System.out.println();
	}

}
