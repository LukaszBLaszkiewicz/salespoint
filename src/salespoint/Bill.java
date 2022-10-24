package salespoint;
import java.util.List;

public class Bill {
    private float productPrice;
    private int amount;
    private float totalPrice;

    float createBill(List<Order> orderList) {
        for (Order o : orderList) {
            productPrice = o.getPrice();
            amount = o.getAmount();
            totalPrice = totalPrice + productPrice * amount;
        }
        return totalPrice;
    }
}
