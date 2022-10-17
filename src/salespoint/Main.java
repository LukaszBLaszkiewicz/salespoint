package salespoint;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //VARIABLES DECLARATIONS
        int switchGroceryStoreMenu; // MAIN MENU SWITCH VARIABLE
        int switchDatabaseMenu; // DATABASE MENU SWITCH VARIABLE
        int menuWhile=0;
        int dbManagerWhile=0;

        //OBJECT DECLARATIONS
        DatabaseManager db = new DatabaseManager();
        //SCANNERS
        Scanner inMainMenu = new Scanner(System.in); // MAIN MENU SCANNER VARIABLE
        Scanner inDBAddProduct = new Scanner(System.in); // VARIABLE TO ADDING PRODUCTS TO DATABASE
        Scanner inDBEditProduct = new Scanner(System.in); // VARIABLE TO EDITING DATABASE

        db.createNewDatabase();
        db.connectToDatabase();
        db.createTableInDatabase();
        //MENU
        while(menuWhile==0){
            System.out.flush();
            System.out.println("===GROCERY STORE===");
            System.out.println("1. Database management");
            System.out.println("2. Make Order");
            System.out.println("3. Close program");
            System.out.println("Your choice: ");
            switchGroceryStoreMenu = inMainMenu.nextInt();
            switch (switchGroceryStoreMenu) {
                case 1:

                    while(dbManagerWhile==0) {
                        System.out.flush();
                        System.out.println();
                        System.out.println("========================DATABASE MANAGER========================");
                        System.out.println("1. Add product to database");
                        System.out.println("2. Delete product from database");
                        System.out.println("3. Show all products");
                        System.out.println("4. Edit product in database");
                        System.out.println("5. Back to Grocery Store menu");
                        System.out.println("Your choice: ");
                        switchDatabaseMenu = inDBAddProduct.nextInt();
                        switch (switchDatabaseMenu) {
                            case 1:
                                System.out.flush();
                                System.out.println("========================ADDING NEW PRODUCT TO DATABASE========================");
                                String test = inDBAddProduct.nextLine();
                                System.out.println("Product name: ");
                                String name = inDBAddProduct.nextLine();
                                System.out.println("Product barcode: ");
                                String barcode = inDBAddProduct.nextLine();
                                System.out.println("Product price: ");
                                Float price = inDBAddProduct.nextFloat();
                                db.addProductToDatabase(name, barcode, price);
                                break;
                            case 2:
                                System.out.flush();
                                System.out.println("========================DELETE PRODUCT FROM DATABASE========================");
                                System.out.println("Type product name");
                                String deleteProduct = inDBEditProduct.nextLine();
                                db.deleteProductFromDatabase(deleteProduct);
                                break;
                            case 3:
                                List<Product> productList = db.copyDatabaseToList();
                                db.showAllDatabase(productList);
                                break;
                            case 4:
                                System.out.flush();
                                System.out.println("========================EDIT PRODUCT IN DATABASE========================");
                                System.out.println("Type product name which you want edit");
                                String editProductName= inDBEditProduct.nextLine();
                                System.out.println("Type new barcode");
                                String editProductBarcode= inDBEditProduct.nextLine();
                                System.out.println("Type new price");
                                Float editProductPrice= inDBEditProduct.nextFloat();
                                db.editProductInDatabase(editProductName,editProductBarcode,editProductPrice);
                                break;
                            case 5:
                                dbManagerWhile=1;
                                break;
                            default:
                                System.out.println("Please type number from 1 to 5!");
                                break;
                        }
                    }
                case 2:

                    //SCANNER VARIABLE
                    Scanner orderProduct = new Scanner(System.in);
                    //LIST

                    //OBJECT


                    System.out.flush();
                    System.out.println("Add product to the order: ");
                    String orderProductName = orderProduct.nextLine();
                    System.out.println("Enter the quantity: ");
                    Integer orderProductAmount = orderProduct.nextInt();
                    Order order = new Order("dupa", "dupa", 1, 2);
                    List<Product> productList = db.copyDatabaseToList();
                    List<Order> orderList=order.makeOrder(productList, orderProductName, orderProductAmount);
                    order.showOrder(orderList);










                    break;
                case 3:
                    //CLOSE PROGRAM
                    break;
            }
        }
    }
}