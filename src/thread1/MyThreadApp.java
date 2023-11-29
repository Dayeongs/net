package thread1;

public class MyThreadApp {
	
	public static void main(String[] args) {
		
		MyThread t1 = new MyThread("1번일꾼");
		MyThread t2 = new MyThread("2번일꾼");
		MyThread t3 = new MyThread("3번일꾼");
		
		t1.start();
		t2.start();
		t3.start();
		
	}

}
