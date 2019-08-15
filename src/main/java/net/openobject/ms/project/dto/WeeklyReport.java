package net.openobject.ms.project.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "WeeklyReport")
@Table(name = "oms_weekly_report")
public class WeeklyReport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long seq;
	
	@Column
	private String weeklyDate;
	
	@Column
	@JsonIgnore
	private Long userSeq;
	
	@Column
	@JsonIgnore
	private Long projectSeq;
	
	@Column
	private String thisWeekTask;
	
	@Column
	private String nextWeekTask;
	
	@Column
	private String issueContents;
	
	@Column
	private String registeredDate;
	
	@Column
	private String modifiedDate;
	
}
