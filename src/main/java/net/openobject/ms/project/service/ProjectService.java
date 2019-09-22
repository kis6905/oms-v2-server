package net.openobject.ms.project.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.openobject.ms.project.dto.UserProject;
import net.openobject.ms.project.dto.UserProject.ProjectRole;
import net.openobject.ms.project.dto.WeeklyReport;
import net.openobject.ms.project.repository.WeeklyReportRepository;
import net.openobject.ms.user.dto.User;
import net.openobject.ms.user.service.UserService;

@Service
public class ProjectService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private WeeklyReportRepository weeklyReportRepository;
	
	@Transactional
	public List<UserProject> getUserProjectList(String userId) {
		User user = userService.getUser(userId);
		List<UserProject> userProjectList = user.getUserProjectList();
		for (UserProject userProject : userProjectList) {
			userProject.setProjectRoleName(ProjectRole.getNameByCode(userProject.getProjectRole()));
		}
		return userProjectList;
	}
	
	public List<WeeklyReport> getWeeklyReportList(Long userSeq, Long projectSeq) {
		return weeklyReportRepository.findAllByUserSeqAndProjectSeq(userSeq, projectSeq);
	}
	
	public WeeklyReport saveWeeklyReport(WeeklyReport weeklyReport) {
		return weeklyReportRepository.save(weeklyReport);
	}
	
}
