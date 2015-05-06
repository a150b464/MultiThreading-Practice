package demo2;

class Runner implements Runnable {

	@Override
	public void run() {

		// TODO Auto-generated method stub
		System.out.println(Thread.currentThread().getContextClassLoader());
		System.out.println(Thread.currentThread().getName());
		System.out.println(Thread.currentThread().getId());
		System.out.println(String.class.getClassLoader());

		for (int i = 0; i <= 10; i++) {
			System.out.println("Number is " + i);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}

public class App {
	public static void main(String[] args) {

		Thread t1 = new Thread(new Runner());
		Thread t2 = new Thread(new Runner());
		t1.start();
		t2.start();

	}
}