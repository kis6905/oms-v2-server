package net.openobject.ms.project.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Entity(name = "Project")
@Table(name = "oms_project")
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long seq;
	
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
	
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime registeredDate;
	
	@LastModifiedDate
	@Column(updatable = true)
	private LocalDateTime modifiedDate;
	
}
