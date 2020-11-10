package Telekocsi.Service;


import Telekocsi.Model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarServiceInterface {

    List<Car> getCarDataOfUser(int userid);
}
