package Telekocsi.Service;

import Telekocsi.Model.Ride;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public interface RideServiceInterface {

    void addRide(String departure, String arrival, String price, String departuretime, String arrivaltime, String plate) throws ParseException;
    List<Ride> getRideByUserId(int id);
    List<Ride> getRideByFromTo(String from, String to);
}
