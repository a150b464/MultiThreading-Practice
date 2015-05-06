package com.synchronization.issues;

//synchronized keyword makes sure each method is executed completely 
//with synchronized there is only one lock, so each thread has to wait for the lock inorder to execute that method
//there is only one intrinsic method for the app4 object.
import java.util.ArrayList;
import java.util.Random;

public class App4 {

	public static void main(String[] args) {
		App4 worker = new App4();


		worker.main();
	}
	private ArrayList<Integer> list1 = new ArrayList<Integer>();
	private ArrayList<Integer> list2 = new ArrayList<Integer>();

	private Random random = new Random();
	private Object lock1 = new Object();
	private Object lock2 = new Object();

	public void stageone() {

		synchronized (lock1) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list1.add(random.nextInt(100));
		}

	}

	public void stagetwo() {

		synchronized (lock2) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list2.add(random.nextInt(100));
		}

	}

	public void process() {
		for (int i = 0; i < 1000; i++) {
			stageone();
			stagetwo();
		}

	}

	public void main() {
		long start = System.currentTimeMillis();
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("Thread 1 running");
				// TODO Auto-generated method stub
				process();
			}

		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("Thread 2 running");
				// TODO Auto-generated method stub
				process();
			}

		});

		t1.start();

		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();

		long time_taken = end - start;

		System.out.println("List 1 size " + list1.size());

		System.out.println("List 2 size " + list2.size());

		System.out.println("Time taken is " + time_taken);
	}

}
