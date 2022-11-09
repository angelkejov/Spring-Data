import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class FindLatest10Projects {

    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();

        int n = 10;
        entityManager.createQuery(
                "SELECT p FROM Project p ORDER BY p.id DESC, p.name ASC", Project.class)
                .setMaxResults(10)
                .getResultStream()
                .forEach(e -> {
                    String format = String.format("Project name: %s%n" +
                            "Project Description: %s%n" +
                            "Project Start Date: %s%n" +
                            "Project End Date: %s", e.getName(), e.getDescription(),
                            e.getStartDate(), e.getEndDate());

                    System.out.println(format);
                });
    }
}
