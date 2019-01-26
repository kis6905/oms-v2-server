package net.openobject.ms.common.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ExceptionHandlingController {
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public String handleException(Exception e) {
		log.error("========== handleException");
		log.error("", e);
		return "error";
	}
	
}
