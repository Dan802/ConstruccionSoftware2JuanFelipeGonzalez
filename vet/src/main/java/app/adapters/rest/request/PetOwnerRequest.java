package app.adapters.rest.request;

public class PetOwnerRequest {
    private long document;
    private String name;
    private int age;
    private String userNameVet;
    private String passwordVet;

    public void setUserNameVet(String userNameVet) {
        this.userNameVet = userNameVet;
    }

    public void setPasswordVet(String passwordVet) {
        this.passwordVet = passwordVet;
    }   

    public String getUserNameVet() {
        return userNameVet;
    }

    public String getPasswordVet() {
        return passwordVet;
    }

    public void setDocument(long document) {
        this.document = document;
    }

    public void setName(String name) {
        this.name = name;
    }   

    public void setAge(int age) {
        this.age = age;
    }   
    
    public long getDocument() {
        return document;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "PetOwnerRequest{" +
                "document=" + document +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }       
}