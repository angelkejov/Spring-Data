package SalesDatabase;

import javax.persistence.*;

@Entity(name = "store_locations")
public class StoreLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "location_name")
    private String locationName;

    public StoreLocation(String locationName) {
        this.locationName = locationName;
    }

    public StoreLocation() {}

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
