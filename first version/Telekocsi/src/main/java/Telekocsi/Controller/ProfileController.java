package Telekocsi.Controller;

import Telekocsi.Model.*;
import Telekocsi.Repository.UserRepository;
import Telekocsi.Service.CarService;
import Telekocsi.Service.ReservationService;
import Telekocsi.Service.RideService;
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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("profile")
public class ProfileController {

    @Autowired
    CarService carService;
    @Autowired
    UserService userService;
    @Autowired
    RideService rideService;
    @Autowired
    ReservationService reservationService;

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
        return new ResponseEntity<>("done", HttpStatus.OK );
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


        return new ResponseEntity<>(jsonStrings, HttpStatus.OK);
    }

    @GetMapping(value = "getRides")
    public ResponseEntity<List<String>> getRides(@RequestParam String id) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        List<Ride> rides = rideService.getRideByUserId(Integer.parseInt(id));

        ArrayList<String> jsonStrings = new ArrayList<String>();
        int i = 0;
        for(Ride ride :rides){
            jsonStrings.add(mapper.writeValueAsString(ride));
            i++;
        }


        return new ResponseEntity<>(jsonStrings, HttpStatus.OK);
    }

    @GetMapping(value = "getReservations")
    public ResponseEntity<List<String>> getReservations(@RequestParam String id) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        List<Reservation> reservations = reservationService.getReservationsByUserId(Integer.parseInt(id));

        ArrayList<String> jsonStrings = new ArrayList<String>();
        int i = 0;
        for(Reservation reservation :reservations){
            System.out.println(reservation.getId());
            jsonStrings.add(mapper.writeValueAsString(reservation));
            i++;
        }

        for(String string : jsonStrings){
            System.out.println(string);
        }

        return new ResponseEntity<>(jsonStrings, HttpStatus.OK);
    }


    @PostMapping(value = "deleteRide")
    public ResponseEntity<String> deleteRide(@RequestBody String datas) throws ParseException {

        String[] data = datas.split(";");

        rideService.deleteRide(data[0], data[1], data[2]);

        return new ResponseEntity<>("tucsok", HttpStatus.OK);
    }

    @GetMapping(value = "deleteCar")
    public ResponseEntity<String> deleteCar(@RequestParam String plate) throws ParseException {

        carService.deleteCarByPlate(plate);

        return new ResponseEntity<>("tucsok", HttpStatus.OK);
    }


}
