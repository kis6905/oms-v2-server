package net.openobject.ms.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.openobject.ms.user.dto.User;
import net.openobject.ms.user.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/user")
@Api(tags = { "사용자 APIs" })
@Slf4j
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "사용자 정보")
	@GetMapping(value = "/user/{userId}")
	public User getUser(@ApiParam(value = "사용자 ID", required = true) @PathVariable(name = "userId") String userId) {
		log.info("-> userId: {}", userId);
		return userService.getUser(userId);
	}
	
}
