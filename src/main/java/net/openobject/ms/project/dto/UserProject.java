package net.openobject.ms.project.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "UserProject")
@Table(name = "oms_user_project")
public class UserProject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long seq;
	
	@ManyToOne
	@JoinColumn(name = "project_seq")
	private Project project;
	
	@Column
	private String approvalYn;
	
	@Column
	private String projectRole;
	
	@Column
	private String inputDate;
	
	@Column
	private String withdrawalDate;
	
	@Column
	private String registeredDate;
	
	@Column
	private String modifiedDate;
	
}
