package Telekocsi.Controller;

import static Telekocsi.Main.*;
import Telekocsi.Model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
@RequestMapping("signup")
public class signupController {

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
        System.out.println(signupdata.getUserName());

        return new ResponseEntity<String>("tucsoooko", HttpStatus.OK);
    }



}
