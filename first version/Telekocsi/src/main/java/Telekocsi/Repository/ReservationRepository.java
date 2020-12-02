package Telekocsi.Repository;

import Telekocsi.Model.Reservation;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public interface ReservationRepository extends CrudRepository<Reservation, Integer> {
    @Query("SELECT res FROM Reservation res JOIN res.ride r JOIN res.user u WHERE u.id = ?1")
    List<Reservation> getReservationsByUserId(int id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM reservations WHERE reservations.id = ?1", nativeQuery = true)
    void deleteReservationById(int id);
}
