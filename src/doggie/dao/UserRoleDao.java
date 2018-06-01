package doggie.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import doggie.user.model.UserRole;

@Repository
@Transactional
public class UserRoleDao {

	@PersistenceContext
	protected EntityManager entityManager;

	public UserRole getRole(String role) {
		try {
			TypedQuery<UserRole> typedQuery = entityManager
					.createQuery("select ur from UserRole ur where ur.role= :role", UserRole.class);
			typedQuery.setParameter("role", role);
			return typedQuery.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public void persist(UserRole userRole) {
		entityManager.persist(userRole);
	}

}
