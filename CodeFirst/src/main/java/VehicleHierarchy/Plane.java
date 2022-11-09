package VehicleHierarchy;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "planes")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Plane extends Vehicle {

    @Column(name = "passenger_capacity")
    private int passengerCapacity;

    private String airline;

    public Plane(String type, String model, BigDecimal price,
                 String fuelType, int passengerCapacity, String airline) {
        super(type, model, price, fuelType);
        this.passengerCapacity = passengerCapacity;
        this.airline = airline;
    }

    public Plane() {
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }
}
