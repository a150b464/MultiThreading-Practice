package com.deadlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

	private Account acc1 = new Account();
	private Account acc2 = new Account();

	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();

	private void acquireLocks(Lock firstlock, Lock secondlock) {
		
		while(true){
			
		}

	}

	private void releaseLocks(Lock lock12, Lock lock22) {
		// TODO Auto-generated method stub

	}

	public void FirstThread() {

		for (int i = 0; i < 10000; i++) {
			acquireLocks(lock1, lock2);

			try {
				Account.balanceTransfer(acc1, acc2, new Random().nextInt(100));

			} finally {
				releaseLocks(lock1, lock2);
			}
		}
	}

	public void secondThread() {

		for (int i = 0; i < 10000; i++) {
			acquireLocks(lock2, lock1);
			try {
				Account.balanceTransfer(acc2, acc1, new Random().nextInt(100));

			} finally {
				releaseLocks(lock1, lock2);

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
