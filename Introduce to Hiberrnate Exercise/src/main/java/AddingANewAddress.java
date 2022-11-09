import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class AddingANewAddress {

    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("PU_Name");

        Scanner sc = new Scanner(System.in);

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        String addressText = "Vitoshka 15";
        Address address = new Address();
        address.getText(addressText);
        entityManager.persist(address);

        String lastName = sc.nextLine();

        entityManager
                .createQuery("UPDATE Employee e" +
                        " SET e.address = :address" +
                        " WHERE e.lastName = :name")
                .setParameter("name", lastName)
                .setParameter("address", address)
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
