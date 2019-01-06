package net.openobject.ms.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.openobject.ms.user.dto.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
}
