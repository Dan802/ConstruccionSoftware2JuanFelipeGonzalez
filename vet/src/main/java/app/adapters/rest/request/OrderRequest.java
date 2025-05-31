package app.adapters.rest.request;

public class OrderRequest {
    private Long orderId;
    private Long petId; 
    private Long documentOwner; 
    private Long documentVet; 
    private Long medicine; 

     // TODO: Delete this
    private String userNameVet;
    private String passwordVet;

    public void setUserNameVet(String userNameVet) {
        this.userNameVet = userNameVet;
    }   

    public String getUserNameVet() {
        return userNameVet;
    }

    public void setPasswordVet(String passwordVet) {
        this.passwordVet = passwordVet;
    }   

    public String getPasswordVet() {
        return passwordVet;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    
    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    public Long getDocumentOwner() {    
        return documentOwner;
    }

    public void setDocumentOwner(Long documentOwner) {
        this.documentOwner = documentOwner;
    }   

    public Long getDocumentVet() {
        return documentVet;
    }

    public void setDocumentVet(Long documentVet) {
        this.documentVet = documentVet;
    }

    public Long getMedicine() {
        return medicine;
    }

    public void setMedicine(Long medicine) {
        this.medicine = medicine;
    }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "\norderId=" + orderId +
                ",\npetId=" + petId +
                ",\ndocumentOwner=" + documentOwner +
                ",\ndocumentVet=" + documentVet +
                ",\nmedicine=" + medicine +
                '}';
    }

}
