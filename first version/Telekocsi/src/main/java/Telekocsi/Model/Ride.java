package Telekocsi.Model;




import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rides")
public class Ride {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "carid")
    private Car car;

    @Column(name="departure")
    private String departure;
    @Column(name="arrival")
    private String arrival;

    @Temporal(TemporalType.TIME)
    @Column(name="departuretime")
    private Date departuretime;

    @Temporal(TemporalType.TIME)
    @Column(name="arrivaltime")
    private Date arrivaltime;

    @Column(name="price")
    private int price;

    public Ride(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public Date getDeparturetime() {
        return departuretime;
    }

    public void setDeparturetime(Date departuretime) {
        this.departuretime = departuretime;
    }

    public Date getArrivaltime() {
        return arrivaltime;
    }

    public void setArrivaltime(Date arrivaltime) {
        this.arrivaltime = arrivaltime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
