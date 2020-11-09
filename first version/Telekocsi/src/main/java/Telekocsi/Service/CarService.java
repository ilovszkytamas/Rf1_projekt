package Telekocsi.Service;

import Telekocsi.Model.Car;
import Telekocsi.Model.User;
import Telekocsi.Repository.CarRepository;
import Telekocsi.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService implements CarServiceInterface{

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private UserRepository userRepository;

    public void sendCar(int userid, String manufac, String type, String plate, int seats, String color, int year){
        try {
            Car car = new Car();
            User user = userRepository.getUserById(userid).get(0);
            car.setUser(user);
            car.setManufacturer(manufac);
            car.setType(type);
            car.setPlate_number(plate);
            car.setSeats(seats);
            car.setColor(color);
            car.setYear(year);
            carRepository.save(car);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

}
