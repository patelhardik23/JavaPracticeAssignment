import java.sql.*;

class MySqlCon {

	public static void main(String args[]) {

		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PFSA", "root", "P@ssw0rd@123");
//here sonoo is database name, root is username and password  
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Table1");
			while (rs.next())
				System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
			System.out.println("SUCCESS");
			
			
			
			
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}