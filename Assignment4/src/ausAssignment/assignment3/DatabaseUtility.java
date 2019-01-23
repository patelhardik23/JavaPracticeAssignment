package ausAssignment.assignment3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtility
{
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

    public DatabaseUtility()
    {
        try
        {

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
            getListoffood();

        }
        catch (SQLException se)
        {
            // Handle errors for JDBC
            se.printStackTrace();
        }
        catch (Exception e)
        {
            // Handle errors for Class.forName
            e.printStackTrace();
        }
    }

    private static void createDatabase() throws SQLException
    {
        System.out.println("Creating database...");

        String sql_stmt = "CREATE DATABASE IF NOT EXISTS " + DB_NAME;

        stmt = conn.createStatement();

        // STEP 4: Execute a query
        stmt.executeUpdate(sql_stmt);

        System.out.println("PFSADB has successfully been created");
    }

    private static void createTables() throws SQLException
    {
        // TODO Auto-generated method stub
        // Create table Food
        String foodTableSql = "CREATE TABLE IF NOT EXISTS Food (\n"
                              + "  foodId INT NOT NULL AUTO_INCREMENT,\n"
                              + "  foodType VARCHAR(45) NOT NULL,\n"
                              + "  name VARCHAR(100) NOT NULL,\n"
                              + "  brand VARCHAR(45) NOT NULL,\n"
                              + "  serveSize VARCHAR(45) NOT NULL,\n"
                              + "  serveUnit VARCHAR(45) NOT NULL,\n"
                              + "  PRIMARY KEY (foodId));";

        stmt = conn.createStatement();
        stmt.executeUpdate(foodTableSql);
//		System.out.println("foodTableSql:::" + foodTableSql);
        System.out.println("Food table has successfully been created");

        // Create table Nutrient
        String nutrientTableSql = "CREATE TABLE IF NOT EXISTS Nutrient (\n"
                                  + "  nutrientId INT NOT NULL,\n"
                                  + "  name VARCHAR(45) NOT NULL,\n"
                                  + "  value VARCHAR(45) NOT NULL,\n"
                                  + "  CONSTRAINT fk_Nutrient_1\n"
                                  + "  FOREIGN KEY (nutrientId)\n"
                                  + "  REFERENCES PFSADB.Food (foodId)\n"
                                  + "  ON DELETE NO ACTION\n"
                                  + "  ON UPDATE NO ACTION);";

        stmt = conn.createStatement();
        stmt.executeUpdate(nutrientTableSql);
        // System.out.println("nutrientTableSql:::" + nutrientTableSql);
        System.out.println("Nutrient table has successfully been created");

        // Create table User
        String userTableSql = "CREATE TABLE IF NOT EXISTS User (\n"
                              + "  userId INT NOT NULL AUTO_INCREMENT,\n"
                              + "  name VARCHAR(100) NOT NULL,\n"
                              + "  PRIMARY KEY (userId));";

        stmt = conn.createStatement();
        stmt.executeUpdate(userTableSql);
        // System.out.println("userTableSql:::" + userTableSql);
        System.out.println("User table has successfully been created");

        // Create table User_food
        String userFoodTableSql = "CREATE TABLE IF NOT EXISTS User_food (\n"
                                  + "  userId INT NOT NULL,\n"
                                  + "  foodId INT NOT NULL,\n"
                                  + "  INDEX fk_User_food_1_idx (userId ASC),\n"
                                  + "  INDEX fk_User_food_2_idx (foodId ASC),\n"
                                  + "  CONSTRAINT fk_User_food_1\n"
                                  + "  FOREIGN KEY (userId)\n"
                                  + "  REFERENCES PFSADB.User (userId)\n"
                                  + "  ON DELETE NO ACTION\n"
                                  + "  ON UPDATE NO ACTION,\n"
                                  + "  CONSTRAINT fk_User_food_2\n"
                                  + "  FOREIGN KEY (foodId)\n"
                                  + "  REFERENCES PFSADB.Food (foodId)\n"
                                  + "  ON DELETE NO ACTION\n"
                                  + "  ON UPDATE NO ACTION);";

        stmt = conn.createStatement();
        stmt.executeUpdate(userFoodTableSql);
        // System.out.println("userFoodTableSql:::" + userFoodTableSql);
        System.out.println("User_food table has successfully been created");
    }

    public void insertDataInProcessedFood(List<ProcessedFood> itemList)
            throws SQLException
    {

        String insertFoodQuery = " INSERT INTO Food (foodType, name,brand,serveSize,serveUnit) VALUES (?,?,?,?,?)";

        for (ProcessedFood processedFood : itemList)
        {

            psStmt = conn.prepareStatement(insertFoodQuery);
            psStmt.setString(1, processedFood.getCategory());
            psStmt.setString(2, processedFood.getName());
            psStmt.setString(3, processedFood.getBrandName());
            psStmt.setInt(4, processedFood.getServeSize());
            psStmt.setString(5, processedFood.getServeUnit());
            psStmt.executeUpdate();
            psStmt.close();

            ProcessedFood pf = getLastProcessedFoodEntry();

            insertNutrientDetailsForFood(pf.getFoodId(),
                    processedFood.getNutrient());
        }
    }

    public ProcessedFood getLastProcessedFoodEntry()
    {
        ProcessedFood processedFood = new ProcessedFood();
        try
        {
            ResultSet rs = getData(
                    "select * from Food where foodId in (select max(foodId) from Food)");

            doResultSetToProcessedFoodMapping(processedFood, rs);
            rs.close();
        }
        catch (Exception e)
        {

        }

        return processedFood;
    }

    void doResultSetToProcessedFoodMapping(ProcessedFood processedFood,
            ResultSet rs)
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

    ResultSet getData(String query)
    {
        ResultSet rs = null;
        try
        {
            psStmt = conn.prepareStatement(query);
            rs = psStmt.executeQuery(query);
        }
        catch (Exception e)
        {

        }
        return rs;
    }

    public void insertDataInUserData(String userName) throws SQLException
    {

        String insertUserQuery = " INSERT INTO User (name) VALUES (?)";
        psStmt = conn.prepareStatement(insertUserQuery);
        psStmt.setString(1, userName);
        psStmt.executeUpdate();
    }

    public void insertDataInUserFood(String user,
            List<ProcessedFood> selectedFoodList) throws SQLException
    {

        String userIdQuery = "select max(userId) as userId from User where name = '"
                             + user + "'";
        ResultSet rs3 = getData(userIdQuery);
        Integer userId = null;
        if (rs3.next())
        {
            userId = rs3.getInt("userId");
        }
        String insertUserFoodQuery = " INSERT INTO User_food VALUES (?,?)";
        for (ProcessedFood selectedFood : selectedFoodList)
        {
            psStmt = conn.prepareStatement(insertUserFoodQuery);
            psStmt.setInt(1, userId);
            psStmt.setInt(2, selectedFood.getFoodId());
            psStmt.executeUpdate();
        }
        rs3.close();
        System.out.println("Inser user and food method");
    }

    public void insertNutrientDetailsForFood(Integer id,
            List<Nutrient> nutrient) throws SQLException
    {

        String insertNutrientQuery = " INSERT INTO Nutrient VALUES (?,?,?)";

        for (Nutrient nutrient2 : nutrient)
        {
            psStmt = conn.prepareStatement(insertNutrientQuery);
            psStmt.setInt(1, id);
            psStmt.setString(2, nutrient2.getNutrientName());
            psStmt.setFloat(3, nutrient2.getNutrientAmount());
            psStmt.executeUpdate();
        }

    }

    public List<ProcessedFood> getListoffood()
    {
        List<ProcessedFood> foodList = new ArrayList<>();
        try
        {
            ResultSet rs1 = getData("select * from Food");
            while (rs1.next())
            {
                ProcessedFood food = new ProcessedFood();
                Integer id = rs1.getInt("foodId");
                food.setFoodId(id);
                food.setCategory(rs1.getString("foodType"));
                food.setName(rs1.getString("name"));
                food.setBrandName(rs1.getString("brand"));
                food.setServeSize(rs1.getInt("serveSize"));
                food.setServeUnit(rs1.getString("serveUnit"));

                List<Nutrient> nutrients = new ArrayList<>();
                ResultSet rs2 = getData(
                        "select * from Nutrient where nutrientId = " + id);
                while (rs2.next())
                {
                    nutrients.add(new Nutrient(rs2.getString("name"),
                            rs2.getFloat("value")));
                }
                rs2.close();

                food.setNutrient(nutrients);
                foodList.add(food);
            }
            rs1.close();
        }
        catch (SQLException e)
        {

        }
        return foodList;
    }
}// end JDBCExample
