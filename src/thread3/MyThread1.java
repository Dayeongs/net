package thread3;

public class MyThread1 extends Thread {
	
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(i);
			try {
				sleep(500);
			} catch (InterruptedException e) {
			}
		}
	}

}
