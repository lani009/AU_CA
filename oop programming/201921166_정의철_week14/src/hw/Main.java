package hw;

import java.io.IOException;

public class Main {
	public static void main (String[] args) {
		ProcessData process = new ProcessData();
		
		try {
			
			System.out.println("input Data");
			process.readData();
			System.out.println("sorting..");
			process.sortData();
			System.out.println("after sorting");
			process.printData();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}