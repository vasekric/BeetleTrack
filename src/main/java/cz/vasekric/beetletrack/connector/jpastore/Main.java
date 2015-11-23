package cz.vasekric.beetletrack.connector.jpastore;

import cz.vasekric.beetletrack.connector.jpastore.models.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by vasek on 18.10.2015.
 */
public class Main {

    public static void main(String[] args) {
        User user = new User();
        user.setUsername("risa8000");
        user.setPassword("risa");

        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("beetletrack-persistence");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction()
                .begin();
        entityManager.persist(user);
        entityManager.getTransaction()
                .commit();
        entityManager.close();

        entityManagerFactory.close();

        System.out.println("JPA-store");
    }


}
