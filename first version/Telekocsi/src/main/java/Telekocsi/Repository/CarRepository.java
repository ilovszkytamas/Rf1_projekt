package Telekocsi.Repository;

import Telekocsi.Model.Car;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car, Integer> {

    @Query(value = "SELECT * FROM cars WHERE ownerid=?1", nativeQuery = true)
    public List<Car> getUserCars(int ownerid);
}
