package salespoint;
import java.util.List;

public class Bill {
    float productPrice;
    int amount;
    float totalPrice;

    float Bill(List<Order> orderList) {
        for (Order o : orderList) {
            productPrice = o.getPrice();
            amount = o.getAmount();
            totalPrice = totalPrice + productPrice * amount;
        }
        return totalPrice;
    }
}
