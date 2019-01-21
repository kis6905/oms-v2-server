package net.openobject.ms.user.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "Role")
@Table(name = "oms_role")
public class Role {
	
	@Id
	private String roleId;
	
	@Column
	private String registeredDate;
	
	@Column
	private String modifiedDate;
	
}
