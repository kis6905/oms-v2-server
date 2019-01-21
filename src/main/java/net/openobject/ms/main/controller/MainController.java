package net.openobject.ms.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.openobject.ms.common.utils.SpringSecurityUtil;
import net.openobject.ms.main.dto.Menu;
import net.openobject.ms.main.service.MainService;

@RestController
@RequestMapping(value = "/main")
@Api(tags = { "메인 화면 APIs" })
@Slf4j
public class MainController {
	
	@Autowired
	private MainService mainService;
	
	@ApiOperation(value = "메뉴 목록")
	@GetMapping(value = "/menu/list")
	public List<Menu> getMenuList() throws Exception {
		
		String userId = SpringSecurityUtil.getUserId();
		List<String> roleList = SpringSecurityUtil.getRoleList();
		
		log.info("userId  : {}", userId);
		log.info("roleList: {}", roleList);
		
		List<Menu> menuList = mainService.getMenuList(roleList);
		
		log.info("menuList: {}", menuList);
		
		return menuList;
	}
	
}
