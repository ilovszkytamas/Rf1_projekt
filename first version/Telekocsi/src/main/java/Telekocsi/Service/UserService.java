package Telekocsi.Service;

import Telekocsi.Model.User;
import Telekocsi.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceInterface{

    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUsers(){

        var users = (List<User>) userRepository.getAllUsers();
        return users;
    }

    public List<User> loginCheck(String username, String password){

        var users = (List<User>) userRepository.findUserLogin(username, password);
        return users;
    }

    public void sendNewUser(String username, String password, String email, String realname){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setRealname(realname);
        user.setIs_Admin(false);
        userRepository.save(user);
    }

    public List<User> getUserData(int id){
        return userRepository.getUserById(id);
    }

}
