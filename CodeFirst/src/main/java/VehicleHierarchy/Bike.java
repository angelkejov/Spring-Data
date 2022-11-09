package VehicleHierarchy;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "bikes")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Bike extends Vehicle {
    public Bike(String type, String model, BigDecimal price, String fuelType) {
        super(type, model, price, fuelType);
    }

    public Bike() {
    }
}
