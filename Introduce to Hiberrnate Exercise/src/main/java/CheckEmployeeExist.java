import entities.Employee;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CheckEmployeeExist {

    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        Scanner sc = new Scanner(System.in);

        String[] input = sc.nextLine().split("\\s+");

        String firstName = input[0];
        String last_name = input[1];

        Long employeeCount = entityManager
                .createQuery("SELECT COUNT(e) FROM Employee e" +
                                " WHERE e.firstName = :first_name" +
                                " AND e.lastName = :last_name",
                        Long.class)
                .setParameter("first_name", firstName)
                .setParameter("last_name", last_name)
                .getSingleResult();

        if (employeeCount > 0) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        entityManager.getTransaction().commit();
    }
}
