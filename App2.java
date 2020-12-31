package kr.ac.sejong.da.first_project;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

public class App2 {

	public static void main(String[] args) {
		MyHashSet<Integer> odd = new MyHashSet<Integer>(70003);
		
		odd.add(1);
		odd.add(3);
		odd.add(5);
		odd.add(7);
		odd.add(9);
		
		MyHashSet<Integer> even = new MyHashSet<Integer>(70003);
		
		even.add(2);
		even.add(4);
		even.add(6);
		even.add(8);
		even.add(10);
		even.add(12);
		even.add(1);
		
		//System.out.println(odd.containsAll(even));
		odd.removeAll(even);
		
		System.out.println(odd.size());
		
		
		/*long time = System.currentTimeMillis();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY.MM.dd a HH:mm:ss");
		date.setTime(time);
		System.out.println(time);
		System.out.println("date format: " + date);
		System.out.println("simple date format: " + sdf.format(date));*/
		
	}

}
