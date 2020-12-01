package Telekocsi.Repository;

import Telekocsi.Model.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface ReservationRepository extends CrudRepository<Reservation, Integer> {
}
