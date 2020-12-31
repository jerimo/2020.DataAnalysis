package kr.ac.sejong.da.first_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class App {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader("C:\\\\Users\\\\김수지\\\\Desktop\\\\data.txt"));

		//Set<Integer> leftSet = new HashSet<Integer>();
		Set<Integer> leftSet = new MyHashSet<Integer>(70003);
		//Set<Integer> rightSet = new HashSet<Integer>();
		Set<Integer> rightSet = new MyHashSet<Integer>(70003);

		while (true) {
			String line = br.readLine();
			if (line == null)
				break;
			if (line.startsWith("#"))
				continue;

			String[] arr = line.split("\t");
			int left = Integer.parseInt(arr[0]);
			leftSet.add(left);
			int right = Integer.parseInt(arr[1]);
			rightSet.add(right);

		}
		br.close();
		System.out.println("load complete");

		System.out.println("Left ID: " + leftSet.size());
		System.out.println("Right ID: " + rightSet.size());
		leftSet.retainAll(rightSet);
		System.out.println("Left ∩ Right: " + leftSet.size());
		
	}
}