package diningPhilosopher;

public class Philosopher implements Runnable {

	private int pid;
	private DiningTable table;
	private String indentation;

	public Philosopher(int pid, DiningTable table) {
		this.pid = pid;
		this.table = table;
		indentation = "";
		for (int i = 0; i < pid; i++) {
			indentation += "\t\t";
		}
	}

	@Override
	public void run() {
		while (true) {
			// Pick
			System.out.println(indentation + "TRY PICK");
			if (pid == 0) {
				table.getRightFork(pid);
				table.getLeftFork(pid);
			} else {
				table.getLeftFork(pid);
				table.getRightFork(pid);
			}
			System.out.println(indentation + "PICK");
			// Eat
			System.out.println(indentation + "EAT");
			table.returnLeftFork(pid);
			table.returnRightFork(pid);
			System.out.println(indentation + "RETURN");
			// Think
			System.out.println(indentation + "THINK");

		}
	}
}
