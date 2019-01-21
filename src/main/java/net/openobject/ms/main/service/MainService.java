package net.openobject.ms.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.openobject.ms.main.dto.Menu;
import net.openobject.ms.main.repository.MenuRepository;

@Service
public class MainService {
	
	@Autowired
	private MenuRepository menuRepository;
	
	public List<Menu> getMenuList(List<String> roleList) {
		return menuRepository.findByRoleIdIn(roleList);
	}
	
}
