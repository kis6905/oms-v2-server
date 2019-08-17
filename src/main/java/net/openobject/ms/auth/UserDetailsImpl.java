package net.openobject.ms.auth;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import net.openobject.ms.user.dto.User;

public class UserDetailsImpl extends org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = 1L;
	
	private Long userSeq;
	private String name;
	private String rank;

	public UserDetailsImpl(String id, long userSeq, List<GrantedAuthority> authorities) {
		super(id, "", authorities);
		this.userSeq = userSeq;
	}

	public UserDetailsImpl(User user, long userSeq, String name, String rank, List<GrantedAuthority> authorities) {
		super(user.getUserId(), user.getRealPassword(), authorities);
		this.userSeq = userSeq;
		this.name = name;
		this.rank = rank;
	}

	public Long getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(Long userSeq) {
		this.userSeq = userSeq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}
	
}
