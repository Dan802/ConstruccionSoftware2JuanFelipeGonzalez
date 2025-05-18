package app.adapters.rest.request;

public class PersonRequest {
    private long document;
    private String name;
    private int age;
    private String role;
    private String userName;
    private String password;
    
    public void setDocument(long document) {
        this.document = document;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public String getUserName() {
        return userName;
    }               

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "PersonRequest [document=" + document + ", name=" + name + ", age=" + age + ", role=" + role + ", userName="
                + userName + ", password=" + password + "]";
    }
}