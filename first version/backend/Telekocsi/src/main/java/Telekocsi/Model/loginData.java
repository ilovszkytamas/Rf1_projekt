package Telekocsi.Model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class loginData {

    private String userName;
    private String password;

    @Autowired
    JdbcTemplate jdbcTemplate;

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

    /*public int first(){
        try {
            jdbcTemplate.execute("INSERT INTO USERS VALUES()");

        }
        catch(Exception ex){
            System.out.println(ex);
            return 0;
        }*/
}
