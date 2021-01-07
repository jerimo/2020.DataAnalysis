package kc.ar.sejong.da.prj3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;

import kr.ac.sejong.da.first_project.MyArrayList;

public class HashSetExample {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\김수지\\Desktop\\data.txt"));

		HashSet<Occurence> os = new HashSet<Occurence>();
		// os는 해시 셋이기 때문에 getOccurence 안됨 -> 그건 Occurence의 getter임
		while (true) {
			String line = br.readLine();
			if (line == null)
				break;
			if (line.startsWith("#"))
				continue;

			String[] arr = line.split("\t");
			int left = Integer.parseInt(arr[0]);
			if (os.contains(new Occurence(left, 0)) == true) {
				for (Occurence occ : os) {
					if (occ.getId() == left) { // occ.id==left
						occ.setOccurence(occ.getOccurence() + 1);
					}
				}
				
			} else {
				os.add(new Occurence(left, 1));
			}
			
			int right = Integer.parseInt(arr[1]);
			if (os.contains(new Occurence(right, 0)) == true) {
				for (Occurence occ : os) {
					if (occ.getId() == right) {
						occ.setOccurence(occ.getOccurence() + 1);
					}
				}
			}
			else {
				os.add(new Occurence(right, 1));
			}
		}
		System.out.println("load complete");
		br.close();

	}

}
