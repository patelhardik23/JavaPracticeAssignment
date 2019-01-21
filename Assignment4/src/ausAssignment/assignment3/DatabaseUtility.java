package ausAssignment.assignment3;

import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseUtility {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "P@ssw0rd@123";
	static final String DB_NAME = "PFSADB";
	static Connection conn = null;
	static Statement stmt = null;
	static PreparedStatement psStmt = null;

	public DatabaseUtility() {
		try {

			// STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			

			// Create Database
			createDatabase();
			conn.setCatalog(DB_NAME);
			// Create tables in database if they do not exist
			createTables();

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
	}

	private static void createDatabase() throws SQLException 
	{
		System.out.println("Creating database...");

		String sql_stmt = "CREATE DATABASE IF NOT EXISTS "+DB_NAME;

		stmt = conn.createStatement();

		// STEP 4: Execute a query
		stmt.executeUpdate(sql_stmt);

		System.out.println("PFSADB has successfully been created");
	}

	private static void closeConnection() {
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
	}

	private static void createTables() throws SQLException {
		// TODO Auto-generated method stub
		// Create table Food
		String foodTableSql = "CREATE TABLE IF NOT EXISTS Food (\n" + "  foodId INT NOT NULL AUTO_INCREMENT,\n"
				+ "  foodType VARCHAR(45) NOT NULL,\n" + "  name VARCHAR(100) NOT NULL,\n"
				+ "  brand VARCHAR(45) NOT NULL,\n" + "  serveSize VARCHAR(45) NOT NULL,\n"
				+ "  serveUnit VARCHAR(45) NOT NULL,\n" + "  PRIMARY KEY (foodId));";

		stmt = conn.createStatement();
		stmt.executeUpdate(foodTableSql);
		System.out.println("foodTableSql:::" + foodTableSql);
		System.out.println("Food table has successfully been created");

		// Create table Nutrient
		String nutrientTableSql = "CREATE TABLE IF NOT EXISTS Nutrient (\n" + "  nutrientId INT NOT NULL,\n"
				+ "  name VARCHAR(45) NOT NULL,\n" + "  value VARCHAR(45) NOT NULL,\n" + "  CONSTRAINT fk_Nutrient_1\n"
				+ "  FOREIGN KEY (nutrientId)\n" + "  REFERENCES PFSADB.Food (foodId)\n" + "  ON DELETE NO ACTION\n"
				+ "  ON UPDATE NO ACTION);";

		stmt = conn.createStatement();
		stmt.executeUpdate(nutrientTableSql);
		System.out.println("nutrientTableSql:::" + nutrientTableSql);
		System.out.println("Nutrient table has successfully been created");

		// Create table User
		String userTableSql = "CREATE TABLE IF NOT EXISTS User (\n" + "  userId INT NOT NULL AUTO_INCREMENT,\n"
				+ "  name VARCHAR(100) NOT NULL,\n" + "  PRIMARY KEY (userId));";

		stmt = conn.createStatement();
		stmt.executeUpdate(userTableSql);
		System.out.println("userTableSql:::" + userTableSql);
		System.out.println("User table has successfully been created");

		// Create table User_food
		String userFoodTableSql = "CREATE TABLE IF NOT EXISTS User_food (\n" + "  userId INT NOT NULL,\n"
				+ "  foodId INT NOT NULL,\n" + "  INDEX fk_User_food_1_idx (userId ASC),\n"
				+ "  INDEX fk_User_food_2_idx (foodId ASC),\n" + "  CONSTRAINT fk_User_food_1\n"
				+ "  FOREIGN KEY (userId)\n" + "  REFERENCES PFSADB.User (userId)\n" + "  ON DELETE NO ACTION\n"
				+ "  ON UPDATE NO ACTION,\n" + "  CONSTRAINT fk_User_food_2\n" + "  FOREIGN KEY (foodId)\n"
				+ "  REFERENCES PFSADB.Food (foodId)\n" + "  ON DELETE NO ACTION\n" + "  ON UPDATE NO ACTION);";

		stmt = conn.createStatement();
		stmt.executeUpdate(userFoodTableSql);
		System.out.println("userFoodTableSql:::" + userFoodTableSql);
		System.out.println("User_food table has successfully been created");

		// closeConnection();

	}

	public void insertDataInProcessedFood(List<ProcessedFood> itemList) throws SQLException {

		String insertFoodQuery = " INSERT INTO Food (foodType, name,brand,serveSize,serveUnit) VALUES (?,?,?,?,?)";

		for (ProcessedFood processedFood : itemList) {

			psStmt = conn.prepareStatement(insertFoodQuery);
			psStmt.setString(1, processedFood.getCategory());
			psStmt.setString(2, processedFood.getBrandName());
			psStmt.setString(3, processedFood.getBrandName());
			psStmt.setInt(4, processedFood.getServeSize());
			psStmt.setString(5, processedFood.getServeUnit());
			psStmt.executeUpdate();
			psStmt.close();
			
			ProcessedFood pf = getLastProcessedFoodEntry();
			
			System.out.println("insertFoodQuery:::" + insertFoodQuery);
			System.out.println("Data inserted in Food Table");
			insertNutrientDetailsForFood(pf.getFoodId(),processedFood.getNutrient());
		}

		// closeConnection();
	}

	public ProcessedFood getLastProcessedFoodEntry() {
		ProcessedFood processedFood = new ProcessedFood();
		try
		{
			ResultSet rs = getData("select * from Food where foodId in (select max(foodId) from Food)");
	
			doResultSetToProcessedFoodMapping(processedFood, rs);
			rs.close();
			System.out.println(processedFood);
		}
		catch (Exception e) {

		}
		
		return processedFood;
	}

	void doResultSetToProcessedFoodMapping(ProcessedFood processedFood, ResultSet rs) 
	{
		try 
		{
			if (rs.next())
			{
				processedFood.setFoodId(rs.getInt("foodId"));
				processedFood.setCategory(rs.getString("foodType"));
				processedFood.setName(rs.getString("name"));
				processedFood.setBrandName(rs.getString("brand"));
				processedFood.setServeSize(rs.getInt("serveSize"));
				processedFood.setServeUnit(rs.getString("serveUnit"));
			}
		}
		catch (Exception e) 
		{

		}
	}

	ResultSet getData(String query) {
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
		} catch (Exception e) {

		}
		return rs;
	}

	public void insertDataInUserData(String userName) throws SQLException {

		String insertUserQuery = " INSERT INTO User (name) VALUES (?)";
		psStmt = conn.prepareStatement(insertUserQuery);
		psStmt.setString(1, userName);
		psStmt.executeUpdate();
		System.out.println("insertUserQuery:::" + insertUserQuery);
		System.out.println("User inserted in User Table");
	}

	public void insertDataInUserFood() {

	}

	public void insertNutrientDetailsForFood(Integer id, List<Nutrient> nutrient) throws SQLException {

		String insertNutrientQuery = " INSERT INTO Nutrient VALUES (?,?,?)";

		for (Nutrient nutrient2 : nutrient) {
			psStmt = conn.prepareStatement(insertNutrientQuery);
			psStmt.setInt(1, id);
			psStmt.setString(2, nutrient2.getNutrientName());
			psStmt.setFloat(3, nutrient2.getNutrientAmount());
			psStmt.executeUpdate();
			
			System.out.println("insertNutrientQuery:::" + insertNutrientQuery);
			System.out.println("Nutrient inserted in Nutrient Table");
		}

	}

	public List<String> getListoffood(String foodType, String foodPreferance) {

		return null;
	}

	/*
	 * public static void main(String[] args) {
	 * 
	 * new DatabaseUtility();
	 * 
	 * }
	 */// end main
}// end JDBCExample