package com.semaphore;

import java.util.concurrent.Semaphore;

public class Connection {
	private static Connection instance = new Connection();

	private Semaphore sem = new Semaphore(20);

	private int connections = 0;

	private Connection() {

	}

	public static Connection getInstance() {

		return instance;

	}

	public void connect() {

		
			try {
				sem.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
			doConnect();
		} finally {
			sem.release();
		}
	}

	public void doConnect() {
		synchronized (this) {
			connections++;
			System.out.println(" Total number of connections " + connections);

		}
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	synchronized(this){
		
		connections --;
	}

}
}