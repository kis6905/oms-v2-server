package net.openobject.ms.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
	
	public List<User> getUserList() {
		return userRepository.findAll(new Sort(Sort.Direction.DESC, "registeredDate"));
	}
	
}
