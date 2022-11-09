import entities.Department;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class IncreaseSalaries {

    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        Scanner sc = new Scanner(System.in);
        List<String> departmentNames = Arrays.asList(
                "Engineering",
                "Tool Design",
                "Marketing",
                "Information Services");

        List<Department> departments = entityManager.createQuery(
                "SELECT d FROM Department d WHERE d.name IN (:names)",
                Department.class)
                .setParameter("names", departmentNames)
                .getResultList();

        entityManager
                .createQuery("UPDATE Employee e" +
                        " SET e.salary = e.salary * 1.12" +
                        " WHERE e.department IN (:departments)", Employee.class)
                .setParameter("departments", departments)
                .executeUpdate();
        for (Department department : departments) {

        }
            System.out.printf("%s %s ($%.2f)", e.getFirstName(), e.getLastName(), e.getSalary());
        }
}
