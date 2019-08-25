package net.openobject.ms.user.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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
	
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime registeredDate;
	
	@LastModifiedDate
	@Column(updatable = true)
	private LocalDateTime modifiedDate;
}
