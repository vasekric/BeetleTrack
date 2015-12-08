package cz.vasekric.beetletrack.connector.jpastore.repositories;

import cz.vasekric.beetletrack.connector.jpastore.mappers.ProjectEntityMapper;
import cz.vasekric.beetletrack.connector.jpastore.mappers.UserEntityMapper;
import cz.vasekric.beetletrack.connector.jpastore.models.Project;
import cz.vasekric.beetletrack.domain.models.ProjectDO;
import cz.vasekric.beetletrack.domain.repository.ProjectRepository;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by vasek on 04.12.2015.
 */
@Singleton
public class JpaProjectRepository implements ProjectRepository {

    @PersistenceContext(name = "beetletrack-persistence")
    private EntityManager em;

    @Inject private ProjectEntityMapper projectMapper;

    @Override
    public ProjectDO save(ProjectDO project) {
        final Project savedProject = em.merge(projectMapper.map(project));
        em.flush();

        return projectMapper.map(savedProject);
    }

    @Override
    public List<ProjectDO> findAll() {
        final List resultList = em.createQuery("SELECT p FROM Project p").getResultList();
        return projectMapper.map(resultList);
    }

    @Override
    public ProjectDO findById(Integer id) {
        final Project project = em.find(Project.class, id);
        return projectMapper.map(project);
    }
}
