package net.openobject.ms.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.openobject.ms.user.dto.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUserId(String userId);
	
}
