package doggie.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import doggie.user.model.UserRole;

@Repository
@Transactional
public interface UserRoleDao extends JpaRepository<UserRole, Integer> {

	UserRole findByRole(String searchString);
}
