package com.yangy.web.interceptor;

import com.alibaba.druid.support.json.JSONUtils;
import com.yangy.web.annotation.CheckRepeatSubmit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Yangy
 * @Date: 2021/8/25 17:26
 * @Description
 */
@Aspect
@Component
@Slf4j
public class AnnotationInterceptor implements HandlerInterceptor {
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Pointcut("@annotation(com.yangy.web.annotation.CheckRepeatSubmit)")
	public void annotationPointCut(){};

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//请求方法是否存在注解
		boolean hasAnnotation = handler.getClass().isAssignableFrom(HandlerMethod.class);
		if(hasAnnotation){
			CheckRepeatSubmit accessAnnotation = ((HandlerMethod)handler).getMethodAnnotation(CheckRepeatSubmit.class);
			if(Objects.nonNull(accessAnnotation)){
				//需要校验是否重复提交
				String methodName = request.getMethod();
				Map<String,String []> paramMap = request.getParameterMap();
				if(MapUtils.isNotEmpty(paramMap)){
					//通过方法名+参数 作为重复提交的校验要素
					String paramStr = JSONUtils.toJSONString(paramMap);
					String cacheKey = StringUtils.join(methodName,":",paramStr);
					if(Objects.nonNull(redisTemplate.opsForValue().get(cacheKey))){
						//重复提交则
						log.info("The method {} repeat to submit!",methodName);
						return false;
					}else{
						//没有则添加校验锁，以便于后续提交的校验。有效期2秒
						redisTemplate.opsForValue().set(cacheKey,"repeat",2,TimeUnit.SECONDS);
					}
				}
			}
		}
		
		return true;
	}
}
