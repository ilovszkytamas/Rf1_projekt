package Telekocsi.Model;


import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class loginData {

    private String userName;
    private String password;

    public loginData(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    public loginData(){}

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }


}
