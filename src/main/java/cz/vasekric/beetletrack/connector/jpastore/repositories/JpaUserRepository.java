package cz.vasekric.beetletrack.connector.jpastore.repositories;

import cz.vasekric.beetletrack.connector.jpastore.mappers.UserEntityMapper;
import cz.vasekric.beetletrack.connector.jpastore.models.User;
import cz.vasekric.beetletrack.domain.models.UserDO;
import cz.vasekric.beetletrack.domain.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;

/**
 * Created by vasek on 04.12.2015.
 */
@Singleton
public class JpaUserRepository implements UserRepository {

    @PersistenceContext(name = "beetletrack-persistence")
    private EntityManager em;

    @Inject private UserEntityMapper userMapper;

    @Override
    public UserDO createUser(UserDO user) {
        final String hashpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        User userEntity = userMapper.map(user);
        userEntity.setPassword(hashpw);
        userEntity = em.merge(userEntity);
        em.flush();
        final UserDO savedUser = userMapper.map(userEntity);
        savedUser.setAuthenticated(true);

        return user;
    }

    @Override
    public UserDO authenticate(UserDO user) {
        try {
            final User findUser = em.createQuery("SELECT u FROM User u WHERE u.username=:username", User.class)
                    .setParameter("username", user.getUsername())
                    .getSingleResult();
            final boolean authenticated = BCrypt.checkpw(user.getPassword(), findUser.getPassword());
            user.setAuthenticated(authenticated);
            user.setId(findUser.getId());
            return user;
        } catch(NoResultException e) {
            user.setAuthenticated(false);
            return user;
        }
    }

    @Override
    public List<UserDO> findAllUsers() {
        final List<User> result = em.createQuery("SELECT u FROM User u").getResultList();
        return userMapper.map(result);
    }

    @Override
    public UserDO findUser(Integer id) {
        final User result = em.find(User.class, id);
        return userMapper.map(result);
    }
}
