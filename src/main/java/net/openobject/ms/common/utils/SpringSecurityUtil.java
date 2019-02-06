package net.openobject.ms.common.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;

import net.openobject.ms.auth.UserDetailsImpl;

public class SpringSecurityUtil {
	
	public static String getUserId() throws Exception {
		UserDetailsImpl userDetailsImpl = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetailsImpl.getUsername();
	}
	
	public static Long getUserSeq() throws Exception {
		UserDetailsImpl userDetailsImpl = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetailsImpl.getUserSeq();
	}
	
	public static List<String> getRoleList() throws Exception {
		return SecurityContextHolder.getContext().getAuthentication().getAuthorities()
					.stream()
					.map(e -> e.toString())
					.collect(Collectors.toList());
	}
	
}
