package SalesDatabase;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Sale() {

    }

    public Product getProduct() {
        return product;
    }

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    @ManyToOne
    @JoinColumn(name = "store_location_id")
    private StoreLocation storeLocation;

    public StoreLocation getStoreLocation() {
        return storeLocation;
    }

    private LocalDate date;

    public Sale(Product product, Customer customer,
                StoreLocation storeLocation, LocalDate date) {
        this.product = product;
        this.customer = customer;
        this.storeLocation = storeLocation;
        this.date = date;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setStoreLocation(StoreLocation storeLocation) {
        this.storeLocation = storeLocation;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
