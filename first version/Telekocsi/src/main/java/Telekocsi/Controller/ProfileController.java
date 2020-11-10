package Telekocsi.Controller;

import Telekocsi.Model.*;
import Telekocsi.Repository.UserRepository;
import Telekocsi.Service.CarService;
import Telekocsi.Service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("profile")
public class ProfileController {

    @Autowired
    CarService carService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "")
    @ResponseBody
    public ModelAndView signup () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("profil");
        return modelAndView;
    }

    @PostMapping(value = "addCar")
    public ResponseEntity<String> addCar(@RequestBody String cardatas) throws IOException {
        System.out.println(cardatas);
        CarData cardata = new ObjectMapper().readValue(cardatas, CarData.class);
        System.out.println(cardata.toString());
        carService.sendCar(Integer.parseInt(cardata.getUserid()), cardata.getManufacturer(), cardata.getType(), cardata.getPlate_number(), Integer.parseInt(cardata.getSeats()), cardata.getColor(), Integer.parseInt(cardata.getYear()));
        return new ResponseEntity<>("faszfejűűűűűű", HttpStatus.OK );
    }

    @GetMapping(value = "getUserData")
    public ResponseEntity<String> getUserData(@RequestParam String id){

        User user = userService.getUserData(Integer.parseInt(id)).get(0);

        System.out.println(id);

        return new ResponseEntity<>(user.getRealname()+";"+user.getUsername(), HttpStatus.OK);
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


}
