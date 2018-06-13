package doggie.user.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import doggie.user.model.User;
import doggie.user.model.UserRole;

@Repository
@Transactional
public interface UserDao extends JpaRepository<User, Integer> {

	List<User> findByUserName(String userName);
}
