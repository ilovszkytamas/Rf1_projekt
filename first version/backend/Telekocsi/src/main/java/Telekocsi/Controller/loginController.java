package Telekocsi.Controller;

import static Telekocsi.Main.*;
import Telekocsi.Model.*;
//import Telekocsi.Entities.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.tomcat.util.json.JSONParser;
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
            //System.out.println(logindata.first());

        return new ResponseEntity<String>("tucsoooko", HttpStatus.OK);
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(Exception e) {
        System.out.println(e);
    }
}
