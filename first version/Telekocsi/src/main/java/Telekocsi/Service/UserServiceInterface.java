package Telekocsi.Service;

import Telekocsi.Model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserServiceInterface {

    List<User> findAllUsers();
    List<User> loginCheck(String username, String password);
    void sendNewUser(String username, String password, String email, String realname);
    List<User> getUserData(int id);

}
