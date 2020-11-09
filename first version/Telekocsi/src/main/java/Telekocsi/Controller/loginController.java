package Telekocsi.Controller;

import static Telekocsi.Main.*;
import Telekocsi.Model.*;
//import Telekocsi.Entities.*;

import Telekocsi.Service.UserServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.tomcat.util.json.JSONParser;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("login")
public class loginController {

    @Autowired
    UserServiceInterface userService;

    @RequestMapping(value = "")
    @ResponseBody
    public ModelAndView login () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @PostMapping(value = "checkuser")
    public ResponseEntity<String> checkUser(@RequestBody String logindatas) throws IOException {
        System.out.println(logindatas);
        loginData logindata = new ObjectMapper().readValue(logindatas, loginData.class);

        List<User> users = userService.loginCheck(logindata.getUserName(), logindata.getPassword());

        if(users.isEmpty()){
            return new ResponseEntity<>("szarlogin", HttpStatus.BAD_REQUEST);
        }

        String userid = String.valueOf(users.get(0).getId());
        return new ResponseEntity<>(userid, HttpStatus.OK);
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(Exception e) {
        System.out.println(e);
    }
}
