package com.util.concurrent.pack;
//Many methods that throw InterruptedException, such as sleep, are designed to cancel their 
//current operation and return immediately when an interrupt is received.

//sleep and join throw Interrupted Exception

public class SleepMessages {
	public static void main(String args[]) throws InterruptedException {
		
		String importantInfo[] = { "Mares eat oats", "Does eat oats",
				"Little lambs eat ivy", "A kid will eat ivy too" };

		for (int i = 0; i < importantInfo.length; i++) {
			// Pause for 4 seconds
			Thread.sleep(4000);
			// Print a message
			System.out.println(importantInfo[i]);
		}
	}
}