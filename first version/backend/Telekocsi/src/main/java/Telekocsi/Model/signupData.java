package Telekocsi.Model;

public class signupData {
    private String name;
    private String userName;
    private String password;
    private String email;

    public signupData(){}

    public signupData(String name, String userName, String password, String email){
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
