package com.reentrant.lock;

public class App {

	public static void main(String[] args) throws Exception {

		final Runner runner = new Runner();

		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					runner.firstThread();
				} finally {
					// TODO Auto-generated catch block
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					runner.secondThread();
				} finally {
					// TODO Auto-generated catch block
				}
			}
		});

		t1.start();
		t2.start();

		t1.join();
		t2.join();

		runner.finished();
	}

}