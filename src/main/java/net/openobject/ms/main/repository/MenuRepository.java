package net.openobject.ms.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.openobject.ms.main.dto.Menu;
import net.openobject.ms.user.dto.User;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
	
	User findByUserId(String userId);
	
}
