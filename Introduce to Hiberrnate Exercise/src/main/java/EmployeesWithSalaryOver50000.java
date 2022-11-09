import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

public class EmployeesWithSalaryOver50000 {

    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        List<String> query = entityManager.createQuery("SELECT e.firstName FROM Employee e\n" +
                "WHERE e.salary > 50000", String.class)
                .getResultList();

        for (String s : query) {
            System.out.println(s);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
