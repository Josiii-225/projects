package DB_Connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;


public class runn {
	ConnecttoDB  ctd;
	Scanner sc = new Scanner(System.in);
	public runn() throws SQLException{
		 ctd = new ConnecttoDB();
	}
	public void Login_Customer() throws SQLException{
		System.out.println("Ten dang nhap");
		String name = sc.nextLine();
		System.out.println("Mat khau");
		String password = sc.nextLine();
		boolean flag = ctd.login(name, password);
		if(flag) {
			System.out.println("Dang nhap thanh cong");
			Display_Menu();
		}else {
			System.out.println("Dang nhap that bai");
			Login_Customer();
		}
	}
	private void Display_Menu() throws SQLException {
		System.out.println("============*============");
		System.out.println("1.Kiem tra so du");
		System.out.println("2.Nop tien");
		System.out.println("3.Rut tien");
		System.out.println("4.Dang xuat");
		System.out.println("============*============");
		int choice = sc.nextInt();
		switch(choice) {
		
			case 1: check_Balance();
				break;
			case 2: deposit_Money();
				break;
			case 3: withDraw_Money();
				break;
			case 4: log_out();
				break;
			default: System.out.println("Hay chon so tu 1 den 4");
		}
		
	}
	public void log_out() throws SQLException {
		System.out.println("Xin cam on");
		ctd.conn.close();
	}
	public void withDraw_Money() throws SQLException {
		System.out.println("Nhap so tien ban muon rut");
		int amount = sc.nextInt();
		boolean rt =ctd.withDrawMoney(ctd.CustomerID, amount);
		if(rt) {
			System.out.println("Ban da rut: "+amount);
			Display_Menu();
		}else {
			System.out.println("So du khong kha dung");
			Display_Menu();
		}
	}
	public void deposit_Money() throws SQLException {
		System.out.println("Nhap so tien ban muon nop: ");
		int amount = sc.nextInt();
		boolean nt=ctd.depositMoney(ctd.CustomerID, amount);
		if(nt) {
			System.out.println("Ban da nop: "+amount);
			Display_Menu();
		}
	}
	public void check_Balance() throws SQLException {
		int balance = ctd.getBalance(ctd.CustomerID);
		System.out.println("So du cua ban: "+balance);
		Display_Menu();
	}
	public static void main(String[] args) throws SQLException {
		runn at1 = new runn();
		at1.Login_Customer();
	}

}
