package thread3;

import java.util.Vector;

public class HorseRunnable implements Runnable {
	
	// 순위별로 경주마의 이름을 저장하는 객체
	private Vector<String> names = new Vector<String>();
	public Vector<String> getNames() {
		return names;
	}
	
	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		for(int i = 0; i <= 100; i++) {
			System.out.println("["+name+"]이 " + i + "미터 전진함...");
			try {
				Thread.sleep((int) (Math.random()*10));
			} catch (Exception ex) {}
		}
		
		System.out.println("["+name+"]이 결승선에 도착함...");
		names.add(name);
	}

}
