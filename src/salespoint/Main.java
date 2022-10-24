package salespoint;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //VARIABLES DECLARATIONS
        int switchGroceryStoreMenu=0; // MAIN MENU SWITCH VARIABLE
        int switchDatabaseMenu=0; // DATABASE MENU SWITCH VARIABLE
        float totalPrice;

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
        mainMenu: while(switchGroceryStoreMenu!=3){
            clearScreen();
            System.out.println("===GROCERY STORE===");
            System.out.println("1. Database management");
            System.out.println("2. Make Order");
            System.out.println("3. Close program");
            System.out.println("Your choice: ");
            switchGroceryStoreMenu = inMainMenu.nextInt();
            switch (switchGroceryStoreMenu) {
                case 1:
                    databaseMenu: while(switchDatabaseMenu!=5) {
                        clearScreen();
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
                                clearScreen();
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
                                clearScreen();
                                System.out.println("========================DELETE PRODUCT FROM DATABASE========================");
                                System.out.println("Type product name");
                                String deleteProduct = inDBEditProduct.nextLine();
                                db.deleteProductFromDatabase(deleteProduct);
                                break;
                            case 3:
                                clearScreen();
                                List<Product> productList = db.copyDatabaseToList();
                                db.showAllDatabase(productList);
                                break;
                            case 4:
                                clearScreen();
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
                                System.out.println("Back to main menu...");
                                continue mainMenu;
                            default:
                                System.out.println("Please type number from 1 to 5!");
                                break;
                        }
                    }
                case 2:
                    clearScreen();
                    //CREATING PRODUCT LIST
                    List<Product> productList = db.copyDatabaseToList();
                    db.showAllDatabase(productList);
                    //CREATING ORDER LIST
                    List<Order> orderList = new ArrayList<>();
                    //CREATING ORDER OBJECT
                    Order order = new Order("a", "a", 1, 1);
                    //FILLING PRODUCT LIST
                    productList = db.copyDatabaseToList();
                    //FILLING ORDER LIST BASIC ON PRODUCT LIST
                    orderList = order.makeOrder(productList);
                    //DISPLAY ORDER LIST
                    order.showOrder(orderList);
                    System.out.println("Total Price: ");
                    Bill bill = new Bill();
                    totalPrice=bill.createBill(orderList);
                    System.out.print(totalPrice);
                    System.out.println();
                    break;
                case 3:
                    System.out.println("Good bye!");
                    return;
                default:
                    System.out.println("Type number from 1 to 3!");
            }
        }
    }
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}