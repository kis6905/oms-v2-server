package net.openobject.ms.admin.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.openobject.ms.user.dto.User;
import net.openobject.ms.user.service.UserService;

@RestController
@RequestMapping(value = "/admin/user")
@Api(tags = { "사용자 관리  APIs" })
public class AdminUserController {
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "사용자 목록")
	@GetMapping(value = "/list")
	public List<User> getUserList() {
		return userService.getUserList();
	}
	
}
