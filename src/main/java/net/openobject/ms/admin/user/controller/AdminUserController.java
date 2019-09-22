package net.openobject.ms.admin.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.openobject.ms.common.utils.DateUtil;
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
	
	@ApiOperation(value = "사용자 저장")
	@PostMapping(value = "/")
	public User postUser(
			@RequestParam(name = "seq", required = false) Long seq,
			@RequestParam(name = "userId", required = true) String userId,
			@RequestParam(name = "password", required = true) String password,
			@RequestParam(name = "name", required = true) String name,
			@RequestParam(name = "rank", required = true) String rank) {
		
		User user = User.builder()
						.seq(seq)
						.userId(userId)
						.password(password)
						.name(name)
						.rank(rank)
						.build();
		
		user.setRegisteredDate(DateUtil.nowLocalDateTime());
		user.setModifiedDate(DateUtil.nowLocalDateTime());
		
		return null;
	}
}
