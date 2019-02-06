package net.openobject.ms.auth;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import net.openobject.ms.user.dto.User;

public class UserDetailsImpl extends org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = 1L;
	
	private Long userSeq;

	public UserDetailsImpl(String id, long userSeq, List<GrantedAuthority> authorities) {
		super(id, "", authorities);
		this.userSeq = userSeq;
	}

	public UserDetailsImpl(User user, long userSeq, List<GrantedAuthority> authorities) {
		super(user.getUserId(), user.getRealPassword(), authorities);
		this.userSeq = userSeq;
	}

	public Long getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(Long userSeq) {
		this.userSeq = userSeq;
	}
	
}
