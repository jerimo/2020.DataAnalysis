package kr.ac.sejong.da.first_project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileReadingApp {
	
	public static void main(String[] args) throws IOException {

		FileReader reader = new FileReader("C:\\Users\\김수지\\Desktop\\data.txt");
		FileWriter writer = new FileWriter("C:\\Users\\김수지\\Desktop\\test.txt");

		BufferedWriter bw = new BufferedWriter(writer);
		BufferedReader br = new BufferedReader(reader);

		int lineNum = 0;
		int numID = 0;

		int leftMax = Integer.MIN_VALUE;
		int leftMin = Integer.MAX_VALUE;
		int rightMax = Integer.MIN_VALUE;
		int rightMin = Integer.MAX_VALUE;

		String[] dataArray = new String[0];
		int idx = 0;
		
		while (true) {
			String line = br.readLine();
			if (line == null) {
				break;
			}
			if (line.startsWith("#") == true) {
				continue;
			}

			String[] newArray = new String[dataArray.length+1];
			for(int i=0;i<dataArray.length;i++) {
				newArray[i]=dataArray[i];
			} // dataArray 내용 복사
			//newArray[newArray.length-1]=line;
			dataArray=newArray;
			dataArray[idx]=line;
			idx++;
			System.out.println(idx);
		}

		System.out.println(dataArray[0]);
		
		br.close();
		bw.close();

	}

}
