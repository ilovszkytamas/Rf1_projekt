package Telekocsi.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Service;

@Service
public class CarData {

    @JsonProperty("plate_number")
    private String plate_number;
    @JsonProperty("manufacturer")
    private String manufacturer;
    @JsonProperty("type")
    private String type;
    @JsonProperty("seats")
    private String seats;
    @JsonProperty("color")
    private String color;
    @JsonProperty("year")
    private String year;
    @JsonProperty("userid")
    private String userid;


    public CarData(String plate_number, String manufacturer, String type, String seats, String color, String year, String userid) {
        this.plate_number = plate_number;
        this.manufacturer = manufacturer;
        this.type = type;
        this.seats = seats;
        this.color = color;
        this.year = year;
        this.userid = userid;
    }

    public CarData(){}

    public String toString(){
        return plate_number + manufacturer + type + seats + color + year + userid;
    }

    public String getPlate_number() {
        return plate_number;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getType() {
        return type;
    }

    public String getSeats() {
        return seats;
    }

    public String getColor() {
        return color;
    }

    public String getYear() {
        return year;
    }

    public String getUserid() {
        return userid;
    }
}
