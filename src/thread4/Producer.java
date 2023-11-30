package thread4;

// 공급자
public class Producer extends Thread {

	private Repo repo;
	public Producer(Repo repo) {
		this.repo = repo;
	}
	
	public void run() {
		try {
			for (int i = 0; i < 10; i++) {
				repo.produce(i);
			}
		} catch (Exception ex) {
			
		}
	}
	
}
