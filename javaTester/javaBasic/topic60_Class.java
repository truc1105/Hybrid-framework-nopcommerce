package javaBasic;

public class topic60_Class{
	
	public int MSSV;
	public String fullName;
	public double diemLT, diemTH;

	public topic60_Class(int mSSV, String fullName, double diemLT, double diemTH) {
		this.MSSV = mSSV;
		this.fullName = fullName;
		this.diemLT = diemLT;
		this.diemTH = diemTH;
	}
	
	public int getMSSV() {
		return this.MSSV;
	}
	
	public void setMSSV(int setMSSV) {
		this.MSSV = setMSSV;
	}
	
	public String getFullName() {
		return this.fullName;
	}
	
	public void setFullName(String setFullName) {
		this.fullName = setFullName;
	}
	
	public double getDiemLT() {
		return this.diemLT;
	}
	
	public double getDiemTH() {
		return this.diemTH;
	}
	
	public void printInfo() {
		System.out.println("Name: "+ fullName);
		System.out.println("MSSV: "+ MSSV);
		System.out.println("Diem LT: "+ diemLT);
		System.out.println("Diem TH: "+ diemTH);
	}
	
	public double TinhDtrb(double diemLT, double diemTH) {
		return (diemLT + diemTH*2)/3;
	}
	
	
	public static void main(String[] args) {
		topic60_Class sv1 = new topic60_Class(1752051, "Thanh Truc", 7.5, 9);
		sv1.printInfo();
		System.out.println("Diem trung binh: "+ sv1.TinhDtrb(sv1.getDiemLT(), sv1.getDiemTH()));
	}
}
