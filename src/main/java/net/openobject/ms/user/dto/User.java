package net.openobject.ms.user.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * security 인증 url 인 /login 호출 시 json으로 "userId", "password"를 보내는데,
 * '@JsonIgnoreProperties'에 "password"가 있으면 objectMapper가 password 값을 넣어주지 않는다.
 * 이 때문에 "password" 필드는 그대로 두되, getPassword()는 빈 값을 리턴해 노출시키지 않으며,
 * 실제 password 값이 필요한 경우 getRealPassword()를 호출하고 '@JsonIgnoreProperties'에 추가해 json 필드에 넣어주지 않는다. 
 * </pre>
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "User")
@Table(name = "oms_user")
@JsonIgnoreProperties(value = { "realPassword" })
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long seq;

	@Column
	private String userId;
	
	@Column
	private String password;
	
	@Column
	private String name;
	
	@Column
	private String rank;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "oms_user_role",
    		   joinColumns = @JoinColumn(name = "user_seq"),
    		   inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roleList;
	
	@Column
	private String registeredDate;
	
	@Column
	private String modifiedDate;
	
	public String getPassword() {
		return "";
	}
	
	public String getRealPassword() {
		return this.password;
	}
}
