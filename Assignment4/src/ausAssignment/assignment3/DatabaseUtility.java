package ausAssignment.assignment3;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtility {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "P@ssw0rd@123";
	static Connection conn = null;
	static Statement stmt = null;

	public DatabaseUtility() {
		try {

			// STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// Create Database
			createDatabase();
			// Create tables in database if they do not exist
			createTables();

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try

	}

	private static void createDatabase() throws SQLException {
		System.out.println("Creating database...");

		String sql_stmt = "CREATE DATABASE IF NOT EXISTS  PFSADB";

		stmt = conn.createStatement();

		// STEP 4: Execute a query
		stmt.executeUpdate(sql_stmt);

		System.out.println("PFSADB has successfully been created");
	}

	private static void createTables() throws SQLException {
		// TODO Auto-generated method stub
		// Create table Food
		String foodTableSql = "CREATE TABLE IF NOT EXISTS PFSADB.Food (\n" + "  foodId INT NOT NULL AUTO_INCREMENT,\n"
				+ "  foodType VARCHAR(45) NOT NULL,\n" + "  name VARCHAR(100) NOT NULL,\n"
				+ "  brand VARCHAR(45) NOT NULL,\n" + "  serveSize VARCHAR(45) NOT NULL,\n"
				+ "  serveUnit VARCHAR(45) NOT NULL,\n" + "  PRIMARY KEY (foodId));";

		stmt = conn.createStatement();
		stmt.executeUpdate(foodTableSql);
		System.out.println("foodTableSql:::" + foodTableSql);
		System.out.println("Food table has successfully been created");

		// Create table Nutrient
		String nutrientTableSql = "CREATE TABLE IF NOT EXISTS PFSADB.Nutrient (\n" + "  nutrientId INT NOT NULL,\n"
				+ "  name VARCHAR(45) NOT NULL,\n" + "  value VARCHAR(45) NOT NULL,\n" + "  CONSTRAINT fk_Nutrient_1\n"
				+ "  FOREIGN KEY (nutrientId)\n" + "  REFERENCES PFSADB.Food (foodId)\n" + "  ON DELETE NO ACTION\n"
				+ "  ON UPDATE NO ACTION);";

		stmt = conn.createStatement();
		stmt.executeUpdate(nutrientTableSql);
		System.out.println("nutrientTableSql:::" + nutrientTableSql);
		System.out.println("Nutrient table has successfully been created");

		// Create table User
		String userTableSql = "CREATE TABLE IF NOT EXISTS PFSADB.User (\n" + "  userId INT NOT NULL AUTO_INCREMENT,\n"
				+ "  name VARCHAR(100) NOT NULL,\n" + "  PRIMARY KEY (userId));";

		stmt = conn.createStatement();
		stmt.executeUpdate(userTableSql);
		System.out.println("userTableSql:::" + userTableSql);
		System.out.println("User table has successfully been created");

		// Create table User_food
		String userFoodTableSql = "CREATE TABLE IF NOT EXISTS PFSADB.User_food (\n" + "  userId INT NOT NULL,\n"
				+ "  foodId INT NOT NULL,\n" + "  INDEX fk_User_food_1_idx (userId ASC),\n"
				+ "  INDEX fk_User_food_2_idx (foodId ASC),\n" + "  CONSTRAINT fk_User_food_1\n"
				+ "  FOREIGN KEY (userId)\n" + "  REFERENCES PFSADB.User (userId)\n" + "  ON DELETE NO ACTION\n"
				+ "  ON UPDATE NO ACTION,\n" + "  CONSTRAINT fk_User_food_2\n" + "  FOREIGN KEY (foodId)\n"
				+ "  REFERENCES PFSADB.Food (foodId)\n" + "  ON DELETE NO ACTION\n" + "  ON UPDATE NO ACTION);";

		stmt = conn.createStatement();
		stmt.executeUpdate(userFoodTableSql);
		System.out.println("userFoodTableSql:::" + userFoodTableSql);
		System.out.println("User_food table has successfully been created");

	}

	public void insertDataInProcessedFood() {

	}

	public void insertDataInUserData() {

	}

	public void insertDataInUserFood() {

	}

	public void insertNutrientDetailsForFood() {

	}

	public List getListoffood(String foodType, String foodPreferance) {
		List data = new List();
		return data;
	}
	
	public static void main(String[] args) {

		new DatabaseUtility();

	}// end main
}// end JDBCExample