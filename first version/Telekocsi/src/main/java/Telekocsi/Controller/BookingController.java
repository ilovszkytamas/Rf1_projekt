package Telekocsi.Controller;


import Telekocsi.Model.Car;
import Telekocsi.Model.Ride;
import Telekocsi.Model.User;
import Telekocsi.Service.CarService;
import Telekocsi.Service.RideService;
import Telekocsi.Service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("booking")
public class BookingController {

    @Autowired
    CarService carService;
    @Autowired
    UserService userService;
    @Autowired
    RideService rideService;

    @RequestMapping(value = "")
    @ResponseBody
    public ModelAndView signup () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("booking");
        return modelAndView;
    }

    @GetMapping(value = "getRides", produces = "application/json; charset=UTF-8")
    public ResponseEntity<List<String>> getRidesFromTo(@RequestParam String from, @RequestParam String to) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();


        System.out.println(from+";"+to);

        List<Ride> rides = rideService.getRideByFromTo(from, to);

        ArrayList<String> jsonStrings = new ArrayList<String>();
        for (Ride ride: rides){
            jsonStrings.add(mapper.writeValueAsString(ride));
            System.out.println(ride.getId());
        }

        for (String string: jsonStrings){
            System.out.println(string);
        }

        return new ResponseEntity<>(jsonStrings, HttpStatus.OK);
    }

}
