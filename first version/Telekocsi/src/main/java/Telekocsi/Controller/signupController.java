package Telekocsi.Controller;

import static Telekocsi.Main.*;
import Telekocsi.Model.*;
import Telekocsi.Service.UserServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
@RequestMapping("signup")
public class signupController {

    @Autowired
    UserServiceInterface userService;

    @RequestMapping(value = "")
    @ResponseBody
    public ModelAndView signup () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("signup");
        return modelAndView;
    }

    @PostMapping(value = "signupData")
    public ResponseEntity<String> checkUser(@RequestBody String signupDatas) throws IOException {
        System.out.println(signupDatas);
        signupData signupdata = new ObjectMapper().readValue(signupDatas, signupData.class);

        userService.sendNewUser(signupdata.getUserName(), signupdata.getPassword(), signupdata.getEmail(), signupdata.getName());

        return new ResponseEntity<String>("tucsoooko", HttpStatus.OK);
    }

}
