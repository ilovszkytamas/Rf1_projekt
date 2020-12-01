package Telekocsi.Service;

import Telekocsi.Model.*;
import Telekocsi.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService implements ReservationServiceInterface{

    @Autowired
    RideRepository rideRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReservationRepository reservationRepository;

    public void bookRide(int rideid, int userid){
        Ride ride = new Ride();
        User user = new User();
        Reservation reservation = new Reservation();

        ride = rideRepository.getRideById(rideid).get(0);
        user = userRepository.getUserById(userid).get(0);

        reservation.setRide(ride);;
        reservation.setUser(user);
        reservationRepository.save(reservation);
    }
}
