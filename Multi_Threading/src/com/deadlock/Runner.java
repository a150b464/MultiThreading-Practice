package com.deadlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

	private Account acc1 = new Account();
	private Account acc2 = new Account();

	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();

	public void FirstThread() {

		for (int i = 0; i < 10000; i++) {
			lock2.lock();
			lock1.lock();

			try {
				Account.balanceTransfer(acc1, acc2, new Random().nextInt(100));

			} finally {
				lock1.unlock();
				lock2.unlock();
			}
		}
	}

	public void secondThread() {

		for (int i = 0; i < 10000; i++) {
			lock1.lock();
			lock2.lock();
			try {
				Account.balanceTransfer(acc2, acc1, new Random().nextInt(100));

			} finally {
				lock1.unlock();
				lock2.unlock();
			}
		}

	}

	public void finished() {
		System.out.println(acc1.getBalance());
		System.out.println(acc2.getBalance());
		System.out.println("Total Balance "
				+ (acc1.getBalance() + acc2.getBalance()));

	}

}
