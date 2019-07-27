package net.openobject.ms.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.openobject.ms.user.dto.User;
import net.openobject.ms.user.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User getUser(String userId) {
		return userRepository.findByUserId(userId);
	}
	
}
