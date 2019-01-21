package net.openobject.ms.common.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
	
	@Before("execution(* net.openobject.ms.*.controller.*.*(..))")
	public void beforeController(JoinPoint joinPoint) {
		if (!log.isInfoEnabled()) return;
		
		Signature signature = joinPoint.getSignature();
		Method method = MethodSignature.class.cast(signature).getMethod();
		Object[] args = joinPoint.getArgs();
		
		log.info("=============================================");
		log.info("{}.{}()", signature.getDeclaringTypeName(), method.getName());
		
		Parameter[] paramsters = method.getParameters();
		Annotation[][] parameterAnnotations = method.getParameterAnnotations();
		
		for (int argIndex = 0; argIndex < args.length; argIndex++) {
			for (Annotation paramAnnotation : parameterAnnotations[argIndex]) {
				if (!(paramAnnotation instanceof PathVariable) &&
					!(paramAnnotation instanceof RequestParam) &&
					!(paramAnnotation instanceof RequestBody) &&
					!(paramAnnotation instanceof ModelAttribute)) {
					continue;
				}
				
				Parameter parameter = paramsters[argIndex];
				Object arg = args[argIndex];
				log.info("param -> {}: {}", parameter.getName(), arg.toString());
			}
		}
		log.info("=============================================");
	}
	
}
