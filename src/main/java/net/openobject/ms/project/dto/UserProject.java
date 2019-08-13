package net.openobject.ms.project.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	
	public enum ProjectRole {
		DEVELOPER_BACK_END("DBE", "Back-End Developer"),
		DEVELOPER_FRONT_END("DFE", "Front-End Developer"),
		DEVELOPER_IOS("DI", "iOS Developer"),
		DEVELOPER_AOS("DA", "Android Developer"),
		PUBLISHER("PB", "Publisher"),
		DESIGNER("DE", "Designer"),
		PLANNER("PN", "Planner"),
		PROJECT_MANAGER("PM", "Project Manager")
		;
		
		private final String code;
		private final String name;
		
		ProjectRole(String code, String name) {
			this.code = code;
			this.name = name;
		}
		public String getCode() {
			return this.code;
		}
		public String getName() {
			return this.name;
		}
		
		public static String getNameByCode(String code) {
			for (ProjectRole projectRole : ProjectRole.values()) {
				if (projectRole.getCode().equals(code)) {
					return projectRole.getName();
				}
			}
			return null;
		}
	}
	
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
	
	@Transient
	private String projectRoleName;
	
	public void setProjectRoleNameByProjectRoleCode(String projectRoleCode) {
		projectRoleName = ProjectRole.getNameByCode(projectRoleCode);
	}
	
	@Column
	private String inputDate;
	
	@Column
	private String withdrawalDate;
	
	@Column
	private String registeredDate;
	
	@Column
	private String modifiedDate;
	
}
