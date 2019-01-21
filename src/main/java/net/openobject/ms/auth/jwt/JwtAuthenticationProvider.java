package net.openobject.ms.auth.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import net.openobject.ms.common.utils.JwtUtil;
import net.openobject.ms.config.CommonConfig;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if (authentication.getCredentials() == null) {
			throw new BadCredentialsException("Bad token(credential is null)");
		}

		String token = authentication.getCredentials().toString();
		
		log.info("isDev      : {}", CommonConfig.isDev());
		log.info("isEnableJwt: {}", CommonConfig.isEnableJwt());
		
		if (CommonConfig.isDev() && !CommonConfig.isEnableJwt()) {
			// Skip JWT authentication
			return new JwtAuthenticationToken("id", "pass", AuthorityUtils.createAuthorityList(token));
		} else {
			if (JwtUtil.verify(token)) {
				UserDetails userDetails = userDetailsService.loadUserByUsername(token);
				return new JwtAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
			} else {
				throw new BadCredentialsException("Bad token(invalid token)");
			}
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return JwtAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
