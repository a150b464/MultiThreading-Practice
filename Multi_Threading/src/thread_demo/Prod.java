/**
 * 
 */
package thread_demo;

import java.util.Scanner;

/**
 * 
 * @author nagendra.yadav
 *  I am trying to explain
 *  method like wait and notifyAll,notify 
 *
 */
class P extends Thread {
	
	private Store store;
	P(Store store) {
		this.store = store;
	}
	public void run() {
		while (true) {
			System.out.println("Enter the msg:");
			Scanner scan = new Scanner(System.in);
			String msg = scan.nextLine();
			store.addMSg(msg);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class Consumer extends Thread {
	Store store;
	Consumer(Store store) {
		this.store = store;
	}
	public void run() {
		while (true) {
			System.out.println("Msg is " + ": " + store.readMsg());
		}
	}
}


//Question : Why notify(),wait(), notifyAll() methods are placed inside the Object class
//instead of Thread class?
//these methods notify(),wait(), notifyAll() deals with shared object not with Thread .
//thread will only use these object.
class Store {
	//this is shared variable
	//which wil be shared between more than one thread
	private String msg;
	
	/**
	 *  before calling this method thread has to take lock of object
	 * @param msg
	 *  
	 *     T1 ->This thread will write some message in msg variable  only when msg is null(empty)
	 *     T2-> this thread will read the message from this msg variable only when msg is not null(full)
	 */
	synchronized void addMSg(String msg) {
		//Question :- why we are calling wait() method inside the while loop instead of if statement?
		//some time waiting thread automatically come into the running state with out notify.
		//this is called spurious wakeup
		//A thread can also wake up without being notified, interrupted, or
	     //* timing out, a so-called <i>spurious wakeup</i>.  While this will rarely
	     ///* occur in practice, applications must guard against it by testing for
	     ///* the condition that should have caused the thread to be awakened, and
	     ///* continuing to wait if the condition is not satisfied.  In other words,
	     //* waits should always occur in loops, like this one:
		while (this.msg != null) {
			try {
				//we can call this function only when we have lock
				wait();   //if any thread call this method , calling thread will move to waiting state for this shared object
				             //thread which is going in wait state it will also release it's lock
			} catch (InterruptedException ie) {
				ie.printStackTrace();
				System.exit(0);
			}
		}
		
		this.msg = msg;
		
		//we can call this function only when we have lock
		notifyAll(); ///awakup all the threads which are waiting for lock of  object of Store class. 
	}
	
	/**
	 * Question 1) How a thread can get a lock?
	 * Answer : 1) By executing synchronized method of object
	 * Answer : 2) By executing synchronized block of object
	 * 
	 * Question 1) How a thread can release it's lock?
	 * Answer 1) By calling wait() 
	 *            2) coming out from  synchronized method of object
	 *            3) By coming out synchronized block of object
	 *   
	 * @return
	 */
	synchronized String readMsg() {
		while (this.msg == null) {
			try {
				wait();
			} catch (InterruptedException ie) {
				ie.printStackTrace();
				System.exit(0);
			}
		}
		String temp = this.msg;
		this.msg = null;
		notifyAll();
		return temp;
	}
}

class Prod {
	public static void main(String args[]) {
		Store st = new Store();
		//st is shared by both the thread
		// p and c
		P p = new P(st);
		Consumer c = new Consumer(st);
		
		p.start();
		c.start();
				
	}
}


//Producer 

//Strore (String msg=null)

//Consumer

