//volatile prevents one thread from caching the value of a variable. 
//Here we have two threads proc1.start and proc1.shutdown.
//each thread can have its own copy which can ensure that it will go in an infinite loop
//volatile keyword helps prevents threads caching values of variables
//volatile guarantees all threads can see the current value of  a variable

package thread_demo;

import java.util.Scanner;

class Processor extends Thread {

	private  boolean running = true;

	public void run() {

		while (running) {
			System.out.println("Hello");

			try {
				Thread.sleep(300);
				
			} catch (InterruptedException e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public void shudown() {

		running = false;
	}
}

public class App2 {

	public static void main(String[] args) {

		Processor proc1 = new Processor();
		Processor proc2 = new Processor();

		proc1.start();
		proc2.start();
		System.out.println("Press enter to stop");
		Scanner scan = new Scanner(System.in);
		scan.nextLine();
		proc1.shudown();
		proc2.shudown();

	}
}