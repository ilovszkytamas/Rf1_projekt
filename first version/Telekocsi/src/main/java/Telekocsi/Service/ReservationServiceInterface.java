package Telekocsi.Service;

import Telekocsi.Model.Reservation;

import java.util.List;

public interface ReservationServiceInterface {
    void bookRide(int rideid, int userid);
    List<Reservation> getReservationsByUserId(int id);
}
