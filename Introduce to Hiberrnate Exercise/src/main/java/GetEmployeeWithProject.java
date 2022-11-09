import entities.Employee;
import entities.Project;

import javax.persistence.*;
import java.util.Scanner;

public class GetEmployeeWithProject {

    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        Scanner sc = new Scanner(System.in);

        int id = Integer.parseInt(sc.nextLine());
        Employee e = entityManager.find(Employee.class, id);
        System.out.printf("%s %s - %s\n", e.getFirstName(), e.getLastName(), e.getJobTitle());
        e.getProjects()
                .stream()
                .map(Project::getName)
                .sorted()
                .forEach(p -> System.out.println("\t" + p));

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
