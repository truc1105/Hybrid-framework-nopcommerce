package javaBasic;

import java.util.Scanner;
import org.testng.annotations.Test;

public class topic_12_String {
	Scanner sc = new Scanner(System.in);
	
	@Test
	public void TC_01_Upper_Case() {
		
		System.out.println("Nhap vao mot chuoi: ");
		String str = sc.nextLine();
		
		char strToArray[] = str.toCharArray();
		int count = 0;
		
		for (char charTemp: strToArray) {
			if(charTemp <= 'Z' && charTemp >= 'A');
			count ++;
		}
		System.out.println("So chu in hoa la: " + count);
	}
}
