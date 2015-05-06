package com.util.concurrent.pack;

import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
//ArrayBlocking Queue are very useful in MT environment
//One thread can add elements while the other can remove
//So if your array is empty, other thread can wait or if your array is full, 
//the thread can wait

public class QueueImpl {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queue<Integer> q1 = new ArrayBlockingQueue<Integer>(3);
		q1.add(10);
		q1.add(12);
		q1.add(25);
		System.out.println("Head of Queue is " + q1.element());
		try {
			q1.add(55);

		} catch (IllegalStateException exception) {
			System.out.println("Queue is full");
		}

		for (Integer value : q1) {
			System.out.println(value);
		}
		System.out.println("Element removed " + q1.remove());
		System.out.println("Element removed " + q1.remove());
		System.out.println("Element removed " + q1.remove());
		try {
			System.out.println("Element removed" + q1.remove());

		} catch (NoSuchElementException exception) {
			System.out.println("Queue is Empty");
		}
		// //////////////////////////////////////////////////////////////////
		// /////// OFFERS, POLL and PEAK //////////////////////
		Queue<Integer> q2 = new ArrayBlockingQueue<Integer>(2);
		System.out.println("Head of Queue 2 is " + q2.peek());

		q2.offer(10);
		q2.offer(25);
		q2.offer(75);

		for (Integer value : q2) {
			System.out.println(value);
		}

		System.out.println("Head of Queue 2 is " + q2.peek());

		System.out.println("Element removed " + q2.poll());
		System.out.println("Element removed " + q2.poll());
		System.out.println("Element removed " + q2.poll());

	}
}
