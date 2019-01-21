package net.openobject.ms.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.openobject.ms.auth.BaseSecurityHandler;
import net.openobject.ms.auth.credential.CredentialAuthenticationProvider;
import net.openobject.ms.auth.credential.filter.CredentialAuthenticationFilter;
import net.openobject.ms.auth.jwt.JwtAuthenticationProvider;
import net.openobject.ms.auth.jwt.JwtInfo;
import net.openobject.ms.auth.jwt.filter.JwtAuthenticationFilter;
import net.openobject.ms.auth.jwt.mather.SkipPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private BaseSecurityHandler securityHandler;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private CredentialAuthenticationProvider credentialProvider;
	
	@Autowired
	private JwtAuthenticationProvider jwtProvider;
	
	private static final String LOGIN_ENTRY_POINT = "/login";
	private static final String TOKEN_ENTRY_POINT = "/token";
	private static final String ERROR_ENTRY_POINT = "/error";
	private static final String ROOT_ENTRY_POINT = "/**";
	private static final String[] SWAGGER_ENTRY_POINTS = { "/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/security", "/swagger-ui.html", "/webjars/**" };
	private static final String[] H2_ENTRY_POINTS = { "/h2" };
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http
			.addFilterBefore(credentialAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(jwtAuthenticationFilter(), FilterSecurityInterceptor.class)
			.csrf()
				.disable()
			.cors()
				.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
			.authorizeRequests()
				.antMatchers(SWAGGER_ENTRY_POINTS).permitAll()
				.antMatchers(H2_ENTRY_POINTS).permitAll()
				.antMatchers(TOKEN_ENTRY_POINT).permitAll()
				.antMatchers(LOGIN_ENTRY_POINT).permitAll()
				.antMatchers(ERROR_ENTRY_POINT).permitAll()
				.antMatchers(ROOT_ENTRY_POINT).hasAnyRole("USER", "ADMIN")
				.anyRequest()
				.authenticated();
    }
	
	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        if (CommonConfig.isDev()) {
        	configuration.setAllowedOrigins(Arrays.asList("*"));
            configuration.setAllowedMethods(Arrays.asList("*"));
            configuration.setAllowedHeaders(Arrays.asList("*"));
        }
        configuration.setExposedHeaders(Arrays.asList(JwtInfo.HEADER_NAME));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
	
	@Override
	public void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.authenticationProvider(credentialProvider)
			   .authenticationProvider(jwtProvider);
	}
	
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/resources/**");
	}
	
	@Bean
	public AntPathRequestMatcher antPathRequestMatcher() {
		return new AntPathRequestMatcher(LOGIN_ENTRY_POINT, HttpMethod.POST.name());
	}

	@Bean
	public CredentialAuthenticationFilter credentialAuthenticationFilter() throws Exception {
		CredentialAuthenticationFilter filter = new CredentialAuthenticationFilter(antPathRequestMatcher(), objectMapper);
		filter.setAuthenticationManager(authenticationManager());
		filter.setAuthenticationSuccessHandler(securityHandler);
		filter.setAuthenticationFailureHandler(securityHandler);
		return filter;
	}

	@Bean
	public SkipPathRequestMatcher skipPathRequestMatcher() {
		List<String> list = new ArrayList<>(Arrays.asList(LOGIN_ENTRY_POINT, TOKEN_ENTRY_POINT, ERROR_ENTRY_POINT));
		list.addAll(new ArrayList<>(Arrays.asList(SWAGGER_ENTRY_POINTS)));
		return new SkipPathRequestMatcher(list);
	}

	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
		JwtAuthenticationFilter filter = new JwtAuthenticationFilter(skipPathRequestMatcher());
		filter.setAuthenticationManager(authenticationManager());
		filter.setAuthenticationFailureHandler(securityHandler);
		return filter;
	}
}
