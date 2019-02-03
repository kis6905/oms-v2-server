package net.openobject.ms.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.openobject.ms.common.utils.SpringSecurityUtil;
import net.openobject.ms.project.dto.UserProject;
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
	public List<UserProject> getCurrentList() throws Exception {
		String userId = SpringSecurityUtil.getUserId();
		
		log.debug("userId: {}", userId);
		
		List<UserProject> list = projectService.getUserProjectList(userId);
		log.debug("list: {}", list);
		return list;
	}
	
}
