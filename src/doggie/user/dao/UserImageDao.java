package doggie.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import doggie.user.model.UserImage;
import doggie.user.model.UserProfile;

@Repository
@Transactional
public interface UserImageDao extends JpaRepository<UserImage, Integer> {
	
	UserImage findByUserProfile(UserProfile userProfile);
}
