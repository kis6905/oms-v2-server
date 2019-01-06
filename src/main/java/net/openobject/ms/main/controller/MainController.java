package net.openobject.ms.main.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.openobject.ms.user.dto.User;

@RestController
@RequestMapping(value = "/main")
@Api(tags = { "메인 화면 APIs" })
@Slf4j
public class MainController {
	
	@ApiOperation(value = "메뉴 목록")
	@GetMapping(value = "/menu/list")
	public User getMenuList() {
		
		return null;
	}
	
}
