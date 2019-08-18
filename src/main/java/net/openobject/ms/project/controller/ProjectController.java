package net.openobject.ms.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.openobject.ms.common.utils.SpringSecurityUtil;
import net.openobject.ms.project.dto.UserProject;
import net.openobject.ms.project.dto.WeeklyReport;
import net.openobject.ms.project.service.ProjectService;

@RestController
@Slf4j
@RequestMapping(value = "/project")
@Api(tags = { "프로젝트 APIs" })
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@ApiOperation(value = "내 프로젝트 목록")
	@GetMapping(value = "/list")
	public List<UserProject> getProjectList() throws Exception {
		String userId = SpringSecurityUtil.getUserId();
		log.debug("userId: {}", userId);
		List<UserProject> list = projectService.getUserProjectList(userId);
		log.debug("list: {}", list);
		return list;
	}
	
	@ApiOperation(value = "특정 프로젝트의 작성한 주간보고 목록")
	@GetMapping(value = "/{projectSeq}/weeklyReport/list")
	public List<WeeklyReport> getProjectWeeks(@PathVariable Long projectSeq) throws Exception {
		Long userSeq = SpringSecurityUtil.getUserSeq();
		log.debug("userSeq: {}", userSeq);
		List<WeeklyReport> list = projectService.getWeeklyReportList(userSeq, projectSeq);
		log.debug("list: {}", list);
		return list;
	}
	
}
