package softuni.exam.models.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "country_name", unique = true, nullable = false)
    private String countryName;

    @Column(nullable = false)
    private String currency;

    public Country(long id, String countryName, String currency) {
        this.id = id;
        this.countryName = countryName;
        this.currency = currency;
    }

    public Country() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String cityName) {
        this.countryName = cityName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return
                countryName + " - " + currency;
    }
}
