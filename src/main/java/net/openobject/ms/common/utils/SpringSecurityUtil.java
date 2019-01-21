package net.openobject.ms.common.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;

public class SpringSecurityUtil {
	
	public static String getUserId() throws Exception {
		return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	public static List<String> getRoleList() throws Exception {
		return SecurityContextHolder.getContext().getAuthentication().getAuthorities()
					.stream()
					.map(e -> e.toString())
					.collect(Collectors.toList());
	}
	
}
