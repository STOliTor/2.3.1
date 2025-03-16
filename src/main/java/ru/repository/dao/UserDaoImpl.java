package ru.repository.dao;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.repository.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

        @PersistenceContext
        private EntityManager entityManager;

        @Override
        @Transactional
        public List<User> viewAll() {
                String hql = "from User";
                Query query = entityManager.createQuery(hql, User.class);
            return (List<User>) query.getResultList();
        }

        @Override
        @Transactional
        public void save(User user) {
                entityManager.persist(user);
        }

        @Override
        public User findById(Long id) {
                return entityManager.find(User.class, id);
        }

        @Override
        @Transactional
        public void redact(User user) {
                entityManager.merge(user);
        }

        @Override
        @Transactional
        public void delete(Long id) {
                User user = entityManager.find(User.class,id);
                if (user != null) {
                        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
                        entityManager.flush();
                }
        }
}
