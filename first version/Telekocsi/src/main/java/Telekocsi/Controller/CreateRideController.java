package Telekocsi.Controller;


import Telekocsi.Model.Car;
import Telekocsi.Service.CarService;
import Telekocsi.Service.RideService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("create")
public class CreateRideController {

    @Autowired
    CarService carService;

    @Autowired
    RideService rideService;

    @RequestMapping("")
    @ResponseBody
    public ModelAndView signup () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("create");
        return modelAndView;
    }

    @GetMapping(value = "getCars")
    public ResponseEntity<List<String>> getCars(@RequestParam String id) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        List<Car> cars = carService.getCarDataOfUser(Integer.parseInt(id));

        ArrayList<String> jsonStrings = new ArrayList<String>();
        int i = 0;
        for(Car car :cars){
            jsonStrings.add(mapper.writeValueAsString(car));
            i++;
        }

        for(String string : jsonStrings){
            System.out.println(string);
        }

        return new ResponseEntity<>(jsonStrings, HttpStatus.OK);
    }

    @PostMapping(value = "addRide")
    public ResponseEntity<String> addRide(@RequestBody String data) throws ParseException {
        System.out.println(data);
        String[] datas = data.split(";");
        rideService.addRide(datas[0],datas[1],datas[2],datas[3],datas[4],datas[5]);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

}
