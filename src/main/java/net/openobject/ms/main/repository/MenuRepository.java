package net.openobject.ms.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.openobject.ms.main.dto.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
	
	List<Menu> findByRoleIdIn(List<String> roleList);
	
}
