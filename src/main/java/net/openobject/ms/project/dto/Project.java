package net.openobject.ms.project.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Entity(name = "Project")
@Table(name = "oms_project")
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long seq;
	
	@Column
	private String projectName;
	
	@Column
	private String projectDescription;
	
	@Column
	private String projectStartDate;
	
	@Column
	private String projectEndDate;
	
	@Column
	private String projectStatus;
	
	@Column
	private String projectClient;
	
	@Column
	private String projectAmount;
	
	@Column
	private String registeredDate;
	
	@Column
	private String modifiedDate;
	
}
