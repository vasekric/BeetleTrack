package cz.vasekric.beetletrack.connector.jpastore;

import cz.vasekric.beetletrack.domain.models.ProjectDO;
import cz.vasekric.beetletrack.connector.jpastore.mappers.ProjectDAOMapper;
import cz.vasekric.beetletrack.connector.jpastore.models.Project;
import cz.vasekric.beetletrack.service.connectors.ProjectRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.List;

/**
 * Created by vasek on 18.10.2015.
 */
//@Named
//@ApplicationScoped
public class ProjectRepositoryImpl implements ProjectRepository, Serializable {

    @Inject private ProjectDAOMapper projectMapper;
    final EntityManager entityManager;

    public ProjectRepositoryImpl() {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("beetletrack-persistence");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public ProjectDO save(ProjectDO project) {
        System.out.println(project);
        final Project projectToSave = projectMapper.map(project);

        entityManager.getTransaction()
                .begin();
        entityManager.persist(projectToSave);
        entityManager.getTransaction()
                .commit();
        entityManager.close();

        return project;
    }

    @Override
    public List<ProjectDO> findAll() {
        return null;
    }

    @Override
    public ProjectDO findById(Integer id) {
        return null;
    }
}
