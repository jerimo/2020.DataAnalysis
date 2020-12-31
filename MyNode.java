package kr.ac.sejong.da.first_project;

import java.io.FileReader;
import java.io.IOException;
import java.util.NavigableSet;
import java.util.TreeSet;

public class MyNode<E> {
	// parent는 사용하지 않아서 삭제하고 높이 height를 추가함
	private E data;
	private MyNode<E> left;
	private MyNode<E> right;
	private int height;

	public MyNode() {
		this.left = null;
		this.right = null;
		this.height = 0;
	}

	public MyNode(Object e) {
		this.data = (E) e;
		this.left = null;
		this.right = null;
		this.height = 0;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public MyNode<E> getLeft() {
		return left;
	}

	public void setLeft(MyNode<E> left) {
		this.left = left;
	}

	public MyNode<E> getRight() {
		return right;
	}

	public void setRight(MyNode<E> right) {
		this.right = right;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "{data: " + this.getData() + " left: " + this.getLeft() + " right: " + this.getRight() + "}";
	}

}
