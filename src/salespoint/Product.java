package salespoint;

public class Product  {
    private String name;
    private String barcode;
    private Integer price;

    public Product(String name, String barcode, Integer price){
        this.name=name;
        this.barcode=barcode;
        this.price=price;
    }

    public String getName(){
        return name;
    }
    public String getBarcode(){
        return barcode;
    }
    public Integer getPrice(){
        return price;
    }
}