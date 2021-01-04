package diningPhilosopher;

public class Application {

	public static void main(String[] args) {
		DiningTable table = new DiningTable();
		// 철학자 5명 시작 시키는 메인 어플리케이션 
		Philosopher r0 = new Philosopher(0, table);
		Philosopher r1 = new Philosopher(1, table);
		Philosopher r2 = new Philosopher(2, table);
		Philosopher r3 = new Philosopher(3, table);
		Philosopher r4 = new Philosopher(4, table);
		
		new Thread(r0).start();
		new Thread(r1).start();
		new Thread(r2).start();
		new Thread(r3).start();
		new Thread(r4).start();
		
	}

}

