package salespoint;

public class Product  {
    private String name;
    private String barcode;
    private Float price;

    public Product(String name, String barcode, Float price){
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
    public Float getPrice(){
        return price;
    }
}