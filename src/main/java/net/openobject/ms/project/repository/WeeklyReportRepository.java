package net.openobject.ms.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.openobject.ms.project.dto.WeeklyReport;

@Repository
public interface WeeklyReportRepository extends JpaRepository<WeeklyReport, Long> {
	
	List<WeeklyReport> findAllByUserSeqAndProjectSeq(Long userSeq, Long projectSeq);
	
}
