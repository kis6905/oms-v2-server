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
import org.springframework.lang.Nullable;

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
	@Nullable
	private Long seq;
	
	@Column
	private String weeklyDate;
	
	@Column
	@JsonIgnore
	@Nullable
	private Long userSeq;
	
	@Column
	@Nullable
	private Long projectSeq;
	
	@Column
	@Nullable
	private String thisWeekTask;
	
	@Column
	@Nullable
	private String nextWeekTask;
	
	@Column
	@Nullable
	private String issueContents;
	
	@CreatedDate
	@Column(updatable = false)
	@Nullable
	private LocalDateTime registeredDate;
	
	@LastModifiedDate
	@Column(updatable = true)
	@Nullable
	private LocalDateTime modifiedDate;
	
}
