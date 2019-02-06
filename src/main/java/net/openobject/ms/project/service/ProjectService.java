package net.openobject.ms.project.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.openobject.ms.project.dto.UserProject;
import net.openobject.ms.user.dto.User;
import net.openobject.ms.user.service.UserService;

@Service
public class ProjectService {
	
	@Autowired
	private UserService userService;
	
	@Transactional
	public List<UserProject> getUserProjectList(String userId) {
		User user = userService.getUser(userId);
		return user.getUserProjectList();
	}
	
}
