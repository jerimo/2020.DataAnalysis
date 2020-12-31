package kr.ac.sejong.da.first_project;

import java.awt.datatransfer.SystemFlavorMap;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.NavigableSet;
import java.util.TreeSet;

public class App3 {

	public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\김수지\\Desktop\\data.txt"));
		
		// left, right ID瑜� TreeSet�� 紐⑤몢 �곸옱�섍린
		//NavigableSet<Integer> data = new TreeSet<Integer>();
		// MyTreeSet�� �꾩꽦�섏뿬 TreeSet怨� 媛숈� 寃곌낵媛믪씠 �섏삤�꾨줉 �섏떆��.
		NavigableSet<Integer> data = new MyTreeSet<Integer>();
		
	
	
		while (true) {
			String line = br.readLine();
			if (line == null)
				break;
			if (line.startsWith("#"))
				continue;
			String[] arr = line.split("\t");
			int left = Integer.parseInt(arr[0]);
			int right = Integer.parseInt(arr[1]);
			data.add(left);
			data.add(right);
		}
		System.out.println("load complete");

		
		
		System.out.println("Min value: " + data.first());
		System.out.println("Max value: " + data.last());
		
		System.out.println("362 is exist?: " + data.contains(362));
		
		br.close();

	}

}
