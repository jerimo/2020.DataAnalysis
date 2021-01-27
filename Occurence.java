package kc.ar.sejong.da.prj3;

public class Occurence {
	private int id;
	private int occurence;
	
	public Occurence() {
		super();
	}

	public Occurence(int id, int occurence) {
		super();
		this.id = id;
		this.occurence = occurence;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOccurence() {
		return occurence;
	}

	public void setOccurence(int occurence) {
		this.occurence = occurence;
	}
	


	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (id == ((Occurence) obj).getId()) {
			return true;
		} else
			return false;
	}

	@Override
	public String toString() {
		return id + " -> " + occurence;
	}
}
