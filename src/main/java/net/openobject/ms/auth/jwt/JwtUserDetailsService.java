package net.openobject.ms.auth.jwt;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.auth0.jwt.interfaces.DecodedJWT;
import net.openobject.ms.auth.UserDetailsImpl;
import net.openobject.ms.common.utils.JwtUtil;

@Component
public class JwtUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String token) {
		DecodedJWT decodedJWT = JwtUtil.tokenToJwt(token);
		
		if (decodedJWT == null) {
			throw new BadCredentialsException("Not used Token");
		}
		
		String userId = decodedJWT.getClaim("userId").asString();
		String roles = decodedJWT.getClaim("roles").asString();
		
		return new UserDetailsImpl(userId, AuthorityUtils.createAuthorityList(roles.split(",")));
	}
}
