package com.yangy.web.interceptor;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.yangy.web.annotation.CheckRepeatSubmit;
import com.yangy.web.exception.RepeatSubmitException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Yangy
 * @Date: 2021/8/25 17:26
 * @Description
 */
@Slf4j
@Aspect
@Component
public class AnnotationInterceptor{
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Pointcut("@annotation(com.yangy.web.annotation.CheckRepeatSubmit)")
	public void annotationPointCut(){}
	
	@Pointcut("execution(* com.yangy.web.controller.*Controller.*(..))")
	public void methodPointCut(){}
	
	@Before("annotationPointCut() && methodPointCut()")
	public boolean doBefore(JoinPoint joinPoint) throws RepeatSubmitException {
		Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
		CheckRepeatSubmit checkRepeatSubmit = method.getAnnotation(CheckRepeatSubmit.class);
		
		if(Objects.nonNull(checkRepeatSubmit)){
			//需要校验是否重复提交
			String methodName = method.getName();
			Object [] paramAry = joinPoint.getArgs();
			if(ArrayUtils.isNotEmpty(paramAry)){
				//通过方法名+参数 作为重复提交的校验要素
				try {
					String paramStr = JSON.toJSONString(paramAry[0]);
					String cacheKey = StringUtils.join(methodName,":",Base64Utils.encodeToString(paramStr.getBytes("UTF-8")));
					if(Objects.nonNull(redisTemplate.opsForValue().get(cacheKey))){
						//重复提交则
						log.error("The method {} repeat to submit....",methodName);
						throw new RepeatSubmitException();
					}else{
						//没有则添加校验锁，以便于后续提交的校验。有效期2秒
						redisTemplate.opsForValue().set(cacheKey,"repeat",2,TimeUnit.SECONDS);
						Thread.sleep(1000);
						return true;
					}
				} catch (Exception e) {
					log.error("AnnotationInterceptor occurs an exception!\n{}",e);
					return false;
				}
			}
		}
		
		return true;
	}
	
}
