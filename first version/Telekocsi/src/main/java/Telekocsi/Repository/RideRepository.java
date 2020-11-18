package Telekocsi.Repository;

import Telekocsi.Model.Ride;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RideRepository extends CrudRepository<Ride, Integer> {

    @Query(value = "SELECT r FROM Ride r JOIN r.car c JOIN c.user u WHERE u.id = ?1")
    public List<Ride> getRideByUserId(int id);

}
