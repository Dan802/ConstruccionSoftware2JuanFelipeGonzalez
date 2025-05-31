package app.adapters.rest.request;

public class SellerRequest {
    private long pet;
    private String product;
    private int count;
    private double price;
    private String userNameSeller;
    private String passwordSeller;
   
    public long getPet() {
        return pet;
    }

    public String getProduct() {
        return product;
    }

    public int getCount() {
        return count;
    }

    public double getPrice() {
        return price;
    }

    public String getUserNameSeller() {
        return userNameSeller;
    }

    public String getPasswordSeller() {
        return passwordSeller;
    }

    public void setPet(long pet) {
        this.pet = pet;
    }

    public void setProduct(String product) {
        this.product = product;
    }   

    public void setCount(int count) {
        this.count = count;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setUserNameSeller(String userNameSeller) {
        this.userNameSeller = userNameSeller;
    }

    public void setPasswordSeller(String passwordSeller) {
        this.passwordSeller = passwordSeller;
    }

    @Override
    public String toString() {
        return "SellerRequest{" +
                "pet=" + pet +
                ", product='" + product + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", userNameSeller='" + userNameSeller + '\'' +  
                ", passwordSeller='" + passwordSeller + '\'' +
                '}';
    }
}