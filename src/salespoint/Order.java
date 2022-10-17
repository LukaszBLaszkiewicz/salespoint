package salespoint;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Order {
    private int amount;
    private Integer productPrice;
    private String name;
    private String barcode;
    private Connection conn;
    private Statement stat;
    public Order(String name, String barcode, int productPrice, int amount){
        this.name=name;
        this.barcode=barcode;
        this.productPrice=productPrice;
        this.amount=amount;
    }

    public List<Order> makeOrder(List<Product> productList, String orderProductName, int orderProductAmount){
        List<Order> orderList = new ArrayList<>();
        for(Product p: productList){
            if(p.getName()==orderProductName){
                name=p.getName();
                barcode=p.getBarcode();
                productPrice=p.getPrice();
                orderList.add(new Order(name,barcode,productPrice,orderProductAmount));
            }
        }
        return orderList;
    }
    public void showOrder(List<Order> orderList){
        System.out.println("=============================ORDER=============================");
        System.out.printf("%10s %25s %25s %25s", "NAME", "BARCODE", "PRICE", "AMOUNT");
        for(Order o: orderList){
            System.out.println();
            System.out.format("%10s %25s %25s %25s", o.getName(), o.getBarcode(), o.getPrice(), o.getAmount());
        }
        System.out.println("=================================================================");
    }
    public String getName(){
        return name;
    }
    public String getBarcode(){
        return barcode;
    }
    public Integer getPrice(){
        return productPrice;
    }
    public Integer getAmount(){
        return amount;
    }

}