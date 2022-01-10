package DB_Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnecttoDB {
	Statement stmt=null;
	ResultSet rs=null;
	Connection conn=null;
	int CustomerID=0;
	int Balance_Money=0;
	
	
	public ConnecttoDB() {
		final String url = "jdbc:mysql://localhost:3306/bank";
		final String user= "root";
		final String password= "";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection(url,user,password);
			System.out.println("Ket Noi Thanh Cong");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public boolean login(String name,String password) throws SQLException {
		stmt=conn.createStatement();
		rs=stmt.executeQuery("select * from customer where CustomerName='"+name+"' and CustomerPassword='"+password+"'");
		if(rs.next()) {
			CustomerID=rs.getInt("CustomerID");
			getBalance(CustomerID);
			return true;
		}else{
			return false;
		}
	}
	public int getBalance(int id) throws SQLException{
		rs = stmt.executeQuery("select BalanceAmount from Account where CustomerID="+id+"");
		if(rs.next()) {
			Balance_Money=rs.getInt("BalanceAmount");
			return Balance_Money;
		}else {
			return 0;
		}
	}
	public boolean withDrawMoney(int id,int amount) throws SQLException { // rut tien
		if(amount > Balance_Money) {
			return false;
		}else {
			stmt.executeUpdate("update Account set BalanceAmount="+(Balance_Money-amount)+" where CustomerID="+id);
			return true;
		}
	}
	public boolean depositMoney(int id,int amount) throws SQLException { // nop tien
			int a=stmt.executeUpdate("update Account set BalanceAmount="+(Balance_Money+amount)+" where CustomerID="+id);
			if(a==1) {
				return true;
			}else {
				return false;
			}
	
			
			
	}
}
