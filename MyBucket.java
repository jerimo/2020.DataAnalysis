package kr.ac.sejong.da.first_project;

import java.util.ArrayList;

public class MyBucket<E> {
	private int hash;
	private ArrayList<E> bucketList;

	public int getHash() {
		return hash;
	}

	public void setHash(int hash) {
		this.hash = hash;
	}

	public ArrayList<E> getBucketList() {
		return bucketList;
	}

	public void setBucketList(ArrayList<E> bucketList) {
		this.bucketList = bucketList;
	}

	public MyBucket(int hash, ArrayList<E> bucketList) {
		super();
		this.hash = hash;
		this.bucketList = bucketList;
	}
	
}