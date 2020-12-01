package Telekocsi.Repository;

import Telekocsi.Model.Car;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car, Integer> {

    @Query(value = "SELECT * FROM cars WHERE ownerid=?1", nativeQuery = true)
    public List<Car> getUserCars(int ownerid);

    @Query(value = "SELECT * FROM cars WHERE plate_number=?1", nativeQuery = true)
    public List<Car> getCarByLicense(String license);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM cars WHERE plate_number=?1", nativeQuery = true)
    public void deleteCarByPlate(String plate);
}
