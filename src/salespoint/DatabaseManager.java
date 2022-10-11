package salespoint;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DatabaseManager {
    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:C:/sqlite/java/connect/net/salespoint/products.db";

    private Connection conn;
    private Statement stat;
    //=====================================================================================================================
    public void createNewDatabase() {

        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    //=====================================================================================================================
    public void connectToDatabase(){
        try {
            Class.forName(DatabaseManager.DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver is missing");
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection(DB_URL);
            stat = conn.createStatement();
            System.out.println("Connection with database established");
            System.out.println(conn);
        } catch (SQLException e){
            System.out.println("Connection error");
            e.printStackTrace();
        }
    }
    //=====================================================================================================================
    public boolean createTableInDatabase(){
        String productTable = "CREATE TABLE IF NOT EXISTS products (name varchar(255), barcode varchar(255), price varchar(255))";
        try {
            stat.execute(productTable);
        }
        catch (SQLException e){
            System.err.println("Error during table creation");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    //=====================================================================================================================
    public boolean addProductToDatabase(String name, String barcode, Float price) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement("insert into products values (?,?,?);");
            prepStmt.setString(1, name);
            prepStmt.setString(2, barcode);
            prepStmt.setFloat(3, price);
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Error during data inserting");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    //=====================================================================================================================
    public boolean deleteProductFromDatabase(String name){
        try{
            PreparedStatement prepStmtDelete = conn.prepareStatement("DELETE FROM products WHERE name = 'zmienna'");
            prepStmtDelete.execute();
        } catch (SQLException e){
            System.err.println("Error during editing data");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    //=====================================================================================================================
    public boolean editProductInDatabase(String name){ // ZLE TU JEST JESZCZE
        try{
            PreparedStatement prepStmtEdit = conn.prepareStatement("UPDATE products SET ? WHERE name = 'zmienna'");
            prepStmtEdit.execute();
        } catch (SQLException e){
            System.err.println("Error during editing data");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    //=====================================================================================================================
    public List<Product> showAllProductsDatabase(){
        List<Product> productList = new ArrayList<Product>();
        try {
            ResultSet allProducts = stat.executeQuery("SELECT * FROM products");
            String name, barcode;
            float price;
            while(allProducts.next()) {
                name = allProducts.getString("name");
                barcode = allProducts.getString("barcode");
                price = allProducts.getFloat("price");
                productList.add(new Product(name, barcode, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return productList;
    }
    //=====================================================================================================================
    public float getProductPrice(String name) {
        float price=0;
        try {
            PreparedStatement oneProduct = conn.prepareStatement("SELECT price FROM products WHERE name = ?");
            oneProduct.setString(1, name);
            ResultSet rs = oneProduct.executeQuery();
            while (rs.next()) {
                price =rs.getFloat("price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return price;
    }
}
