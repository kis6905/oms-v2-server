package net.openobject.ms.auth.credential;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import net.openobject.ms.auth.UserDetailsImpl;
import net.openobject.ms.user.dto.User;
import net.openobject.ms.user.repository.UserRepository;

@Component
@Slf4j
public class CredentialUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userId) {
		log.info("~~ loadUserByUsername() [userId = {}]", userId);
		User user = userRepository.findByUserId(userId);
		
		if (user == null) {
			log.info("~~ user is null");
			throw new UsernameNotFoundException("Not found user, " + userId);
		}
		log.info("~~ user = {}", user.toString());
		
		String[] roles = user.getRoleList().stream()
							.map(e -> e.getRoleName())
							.toArray(String[]::new);
		
		return new UserDetailsImpl(user, AuthorityUtils.createAuthorityList(roles));
	}
}
