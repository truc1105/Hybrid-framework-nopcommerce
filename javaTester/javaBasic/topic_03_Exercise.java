package javaBasic;
import java.util.Scanner;
import java.util.Arrays;
import org.testng.annotations.Test;


public class topic_03_Exercise {
	
	public void TC_01_DataType() {
		int a = 6; int b = 2;

		System.out.println(a + " + " + b + " = " + (a+b));
		System.out.println(a + " - " + b + " = " + (a-b));
		
		float d = 7.5f, r = 3.8f;
		String name = "Automation Testing";
		
		System.out.println("Chu vi = " + (d+r)/2);
		System.out.println("Dien tich = " + d*r);
		System.out.println("Hello " + name);
	}
	
	Scanner scan = new Scanner(System.in); 
	
	public void TC_02_Operator() {
		
		System.out.println("Ten: ");
		String name = scan.next();  
		
	}
	
	public void TC_03_Switch_Case() {
		System.out.println("Enter number: ");
		int numb = scan.nextInt();
		
		switch (numb){
			case 0: 
				System.out.println("Zero");
				break;
			case 1: 
				System.out.println("One");
				break;
			case 2:
				System.out.println("Two");
				break;
			case 3:
				System.out.println("Three");
				break;
			case 4:
				System.out.println("Four");
				break;
		}
	}
	
	
	public void TC_04_Do_While() {
		System.out.println("Enter number: ");
		int numb = scan.nextInt();
		
		if(numb % 2 == 0) {
			do {
				System.out.println(numb);
				numb = numb + 2;
			} while(numb <= 100);
		}
		else
			System.out.println("nope");
	}
	
	public void TC_05_Array() {
		int[] a = new int [] {111, -25, -9, -19, 0};
		
		int temp = a[0];
		for (int i = 1; i < a.length; i++) {
			if (temp < a[i])
				temp = a[i];
		}
		System.out.println("So lon nhat la: " + temp);
	}

}
