package VehicleHierarchy;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "trucks")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Truck extends Vehicle {

    @Column(name = "load_capacity")
    private double loadCapacity;

    public Truck(String type, String model, BigDecimal price,
                 String fuelType, double loadCapacity) {
        super(type, model, price, fuelType);
        this.loadCapacity = loadCapacity;
    }

    public Truck() {
    }

    public double getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }
}
