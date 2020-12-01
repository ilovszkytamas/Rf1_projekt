package Telekocsi.Service;

import Telekocsi.Model.Car;
import Telekocsi.Model.Ride;
import Telekocsi.Repository.CarRepository;
import Telekocsi.Repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class RideService implements RideServiceInterface {

    @Autowired
    CarRepository carRepository;

    @Autowired
    RideRepository rideRepository;

    public void addRide(String departure, String arrival,  String departuretime, String arrivaltime, String price, String plate) throws ParseException {
        Car car = carRepository.getCarByLicense(plate).get(0);
        Ride ride = new Ride();
        ride.setCar(car);
        ride.setDeparture(departure);
        ride.setArrival(arrival);
        ride.setPrice(Integer.parseInt(price));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        formatter.setLenient(false);
        System.out.println(formatter.parse(departuretime));
        ride.setDeparturetime(formatter.parse(departuretime));
        ride.setArrivaltime(formatter.parse(arrivaltime));
        rideRepository.save(ride);
    }

    public List<Ride> getRideByUserId(int id){
        return rideRepository.getRideByUserId(id);
    }

    public List<Ride> getRideById(int id){
        return rideRepository.getRideById(id);
    }

    public List<Ride> getRideByFromTo(String from, String to){
        return rideRepository.getRideByFromTo(from, to);
    }

    public void deleteRide(String plate, String departure, String arrival) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        formatter.setLenient(false);
        rideRepository.deleteRideByPlateAndTime(plate, formatter.parse(departure), formatter.parse(arrival));
    }

}
