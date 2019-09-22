package net.openobject.ms.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import net.openobject.ms.user.dto.User;
import net.openobject.ms.user.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User getUser(String userId) {
		return userRepository.findByUserId(userId);
	}
	
	public List<User> getUserList() {
		return userRepository.findAll(new Sort(Sort.Direction.DESC, "registeredDate"));
	}
	
	public User saveUser(User user) {
		Long seq = user.getSeq();
		String password = user.getPassword();
		if (seq != null && StringUtils.isEmpty(password)) {
			Optional<User> existUserOptional = userRepository.findById(user.getSeq());
			if (!existUserOptional.isEmpty()) {
				user.setPassword(passwordEncoder.encode(existUserOptional.get().getPassword()));
			}
		}
		
		// TODO: password 수정은 DTO로 리팩토링 후 수정
		
		return userRepository.save(user);
	}
	
}
