package salespoint;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //OBJECT DECLARATIONS
        Scanner inMainMenu = new Scanner(System.in); // MAIN MENU SCANNER VARIABLE
        Scanner inDBAddProduct = new Scanner(System.in); // VARIABLE TO ADDING PRODUCTS TO DATABASE
        Scanner inDBEditProduct = new Scanner(System.in); // VARIABLE TO EDITING DATABASE
        Scanner orderProduct = new Scanner(System.in); // VARIABLE TO MAKING ORDER

        //VARIABLES DECLARATIONS
        int switchGroceryStoreMenu; // MAIN MENU SWITCH VARIABLE
        int switchDatabaseMenu; // DATABASE MENU SWITCH VARIABLE


        //MENU
        System.out.println("===GROCERY STORE===");
        System.out.println("1. Database management");
        System.out.println("2. Make Order");
        System.out.println("3. Close program");
        System.out.println("Your choice: ");
        switchGroceryStoreMenu = inMainMenu.nextInt();
        switch (switchGroceryStoreMenu) {
            case 1:
                DatabaseManager db = new DatabaseManager();
                db.createNewDatabase();
                db.connectToDatabase();
                db.createTableInDatabase();
                System.out.println("===DATABASE MANAGER===");
                System.out.println("1. Add product to database");
                System.out.println("2. Edit product in database");
                System.out.println("3. Show all products");
                System.out.println("4. Back to Grocery Store menu");
                System.out.println("Your choice: ");
                switchDatabaseMenu = inDBAddProduct.nextInt();
                switch (switchDatabaseMenu){
                    case 1:
                        System.out.println("===ADDING NEW PRODUCT TO DATABASE===");
                        System.out.println("Product name: ");
                        String name = inDBAddProduct.nextLine();
                        System.out.println("Product barcode: ");
                        String barcode = inDBAddProduct.nextLine();
                        System.out.println("Product price: ");
                        Float price = inDBAddProduct.nextFloat();
                        db.addProductToDatabase(name,barcode,price);
                        break;
                    case 2:
                        System.out.println("===EDIT PRODUCT PRICE IN DATABASE===");
                        System.out.println("Type product name");
                        String editProduct = inDBEditProduct.nextLine();
                        //SEARCHING DB AND CHANGING DATA
                        break;
                    case 3:
                        List<Product> productList = db.showAllProductsDatabase();
                        System.out.println("========================PRODUCTS DATABASE========================");
                        System.out.printf("%10s %25s %25s", "NAME", "BARCODE", "PRICE");
                        for(Product p: productList){
                            System.out.println();
                            System.out.format("%10s %25s %25s", p.getName(), p.getBarcode(), p.getPrice());
                        }
                        break;
                    case 4:
                        //BACK TO MAIN MENU METHOD
                        break;
                }
            case 2:
                DatabaseManager dbOrder = new DatabaseManager();
                Order orderObj = new Order();
                String name, barcode;
                Float price,totalPrice;
                List<Product> productList = new ArrayList<Product>();
                productList = dbOrder.showAllProductsDatabase();
                List<Product> orderList = new ArrayList<Product>();
                // WHILE I=0
                System.out.flush();
                System.out.println("========================PRODUCTS DATABASE========================");
                System.out.printf("%10s %25s %25s", "NAME", "BARCODE", "PRICE");
                for(Product p: productList){
                    System.out.println();
                    System.out.format("%10s %25s %25s", p.getName(), p.getBarcode(), p.getPrice());
                }
                System.out.println("=================================================================");
                System.out.println("Add product to the order: ");
                String orderProductName = orderProduct.nextLine();

                for(Product p: productList){
                    if(p.getName()==orderProductName){
                        name=p.getName();
                        barcode=p.getBarcode();
                        price=p.getPrice();
                        orderList.add(new Product(name,barcode,price);
                    }
                }
                System.out.println("Enter the quantity: ");
                Integer orderProductAmount = orderProduct.nextInt();
                totalPrice = orderObj.Bill(orderProductName,orderProductAmount);
                // TUTAJ MUSI BYC KLASA KTORA MA JESZCZE CENE ORPOCZ WSZYSTKICH CECH PRODUKTU


                break;
            case 3:
                //CLOSE PROGRAM
                break;
        }
    }
}
