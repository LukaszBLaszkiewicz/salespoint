package salespoint;

import javax.xml.crypto.Data;

public class Order {
    int amount;
    float totalPrice;
    float productPrice;
    String name;
    String barcode;
    public Order(String name, String barcode, float productPrice, int amount, float totalPrice){
        name=this.name;
        barcode=this.barcode;
        productPrice=this.productPrice;
        amount=this.amount;
        totalPrice=this.totalPrice;
    }

    float Bill(String name, int amount){
        DatabaseManager db = new DatabaseManager();
        productPrice=db.getProductPrice(name);
        totalPrice = totalPrice + productPrice*amount;

        return totalPrice;
    }
}