package salespoint;

public class Bill {
    float productPrice;
    float totalPrice;
    float Bill(String name, int amount){
        DatabaseManager db = new DatabaseManager();
        productPrice=db.getProductPrice(name);
        totalPrice = totalPrice + productPrice*amount;
        return totalPrice;
    }
}
