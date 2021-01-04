package diningPhilosopher;

import java.util.concurrent.Semaphore;

public class DiningTable {

	private Semaphore fork0;
	private Semaphore fork1;
	private Semaphore fork2;
	private Semaphore fork3;
	private Semaphore fork4;

	public DiningTable() {
		this.fork0 = new Semaphore(1);
		this.fork1 = new Semaphore(1);
		this.fork2 = new Semaphore(1);
		this.fork3 = new Semaphore(1);
		this.fork4 = new Semaphore(1);
	}

	public void getLeftFork(int pid) {
		try {
			if (pid == 0)
				fork4.acquire();
			else if (pid == 1)
				fork0.acquire();
			else if (pid == 2)
				fork1.acquire();
			else if (pid == 3)
				fork2.acquire();
			else if (pid == 4)
				fork3.acquire();
		} catch (InterruptedException e) {

		}
	}

	public void getRightFork(int pid) {
		try {
			if (pid == 0) {
				fork1.acquire();
			} else if (pid == 1) {
				fork2.acquire();
			} else if (pid == 2) {
				fork3.acquire();
			} else if (pid == 3) {
				fork4.acquire();
			} else {
				fork0.acquire();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void returnLeftFork(int pid) {
		try {
			if (pid == 0) {
				fork4.release();
			} else if (pid == 1) {
				fork0.release();
			} else if (pid == 2) {
				fork1.release();
			} else if (pid == 3) {
				fork2.release();
			} else {
				fork3.release();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void returnRightFork(int pid) {
		try {
			if (pid == 0) {
				fork1.release();
			} else if (pid == 1) {
				fork2.release();
			} else if (pid == 2) {
				fork3.release();
			} else if (pid == 3) {
				fork4.release();
			} else {
				fork0.release();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Semaphore getFork0() {
		return fork0;
	}

	public void setFork0(Semaphore fork0) {
		this.fork0 = fork0;
	}

	public Semaphore getFork1() {
		return fork1;
	}

	public void setFork1(Semaphore fork1) {
		this.fork1 = fork1;
	}

	public Semaphore getFork2() {
		return fork2;
	}

	public void setFork2(Semaphore fork2) {
		this.fork2 = fork2;
	}

	public Semaphore getFork3() {
		return fork3;
	}

	public void setFork3(Semaphore fork3) {
		this.fork3 = fork3;
	}

	public Semaphore getFork4() {
		return fork4;
	}

	public void setFork4(Semaphore fork4) {
		this.fork4 = fork4;
	}

}

