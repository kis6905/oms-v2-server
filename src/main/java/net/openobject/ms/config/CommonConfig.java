package net.openobject.ms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class CommonConfig {
	
	public static String mode;
	private static String jwtEnable;

	@Value("${spring.profiles.active}")
	public void setMode(String param) {
		mode = param;
		
		log.info("==================================================");
		log.info("Server Mode: {}", param);
		log.info("==================================================");
	}
	
	@Value("${jwt.enable}")
	public void setJwtEnable(String param) {
		jwtEnable = param;
	}
	
	public static boolean isLocal() {
		return  (mode != null && mode.equals("L"));
	}
	
	public static boolean isDev() {
		return  (mode != null && mode.equals("D"));
	}
	
	public static boolean isEnableJwt() {
		return (jwtEnable == null || Boolean.valueOf(jwtEnable));
	}
	
}
