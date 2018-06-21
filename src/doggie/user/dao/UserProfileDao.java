package doggie.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import doggie.user.model.User;
import doggie.user.model.UserProfile;

@Repository
@Transactional
public interface UserProfileDao extends JpaRepository<UserProfile, Integer> {

	UserProfile findByUser(User user);
}
