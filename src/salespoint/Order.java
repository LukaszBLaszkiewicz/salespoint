package salespoint;

import javax.xml.crypto.Data;

public class Order {
    int amount;
    float totalPrice;
    float productPrice;
    String name;
    String barcode;


    float Bill(String name, int amount){
        DatabaseManager db = new DatabaseManager();
        productPrice=db.getProductPrice(name);
        totalPrice = totalPrice + productPrice*amount;

        return totalPrice;
    }
}