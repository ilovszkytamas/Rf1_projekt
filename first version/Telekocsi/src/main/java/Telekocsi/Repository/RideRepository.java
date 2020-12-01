package Telekocsi.Repository;

import Telekocsi.Model.Ride;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface RideRepository extends CrudRepository<Ride, Integer> {

    @Query("SELECT r FROM Ride r JOIN r.car c JOIN c.user u WHERE u.id = ?1")
    List<Ride> getRideByUserId(int id);

    @Query("SELECT r FROM Ride r JOIN r.car c JOIN c.user u WHERE r.departure = ?1 AND r.arrival = ?2")
    List<Ride> getRideByFromTo(String from, String to);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM rides WHERE rides.departuretime = ?2 AND rides.arrivaltime = ?3 AND carid = (SELECT id FROM cars WHERE cars.plate_number = ?1)", nativeQuery = true)
    void deleteRideByPlateAndTime(String plate, Date departure, Date arrival);
}
