package com.synchronization.issues;

public class App3 {
	private  int count = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		App3 app3 = new App3();
		app3.dowork();
	}

	public synchronized void increment() {
		count++;

	}

	public void dowork() {
		Thread t1 = new Thread(new Runnable() {

	
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 10000; i++) {
					increment();

				}
			}

		});
	
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 10000; i++) {
					increment();

				}
			}

		});
		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		System.out.println("Count: " + count);
	}

}
