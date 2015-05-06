package com.low_level.synchronisation;

import java.util.Scanner;

public class Processor {
	public void produce() throws InterruptedException {

		synchronized (this) {
			System.out.println("Producer thread running");
			wait();
			System.out.println("Resumed");
		}
	}

	public void consume() throws InterruptedException {
		Scanner scan = new Scanner(System.in);

		Thread.sleep(2000);
		synchronized (this) {
			System.out.println("Waiting for return Key");
			scan.nextLine();
			System.out.println("Notify Pressed");
			notify();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
