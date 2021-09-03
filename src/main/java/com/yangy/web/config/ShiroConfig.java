package com.yangy.web.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.yangy.web.realm.AccountRealm;
import com.yangy.web.realm.KickoutSessionControlFilter;
import com.yangy.web.realm.RedisCacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Yangy
 * 这是shiro的配置文件
 * （1）anon：匿名过滤器，表示通过了url配置的资源都可以访问，例：“/statics/**=anon”表示statics目录下所有资源都能访问
 * （2）authc：基于表单的过滤器，表示通过了url配置的资源需要登录验证，否则跳转到登录，例：“/unauthor.jsp=authc”如果用户没有登录访问unauthor.jsp则直接跳转到登录
 * （3）authcBasic：Basic的身份验证过滤器，表示通过了url配置的资源会提示身份验证，例：“/welcom.jsp=authcBasic”访问welcom.jsp时会弹出身份验证框
 * （4）perms：权限过滤器，表示访问通过了url配置的资源会检查相应权限，例：“/statics/**=perms["user:add:*,user:modify:*"]“表示访问statics目录下的资源时只有新增和修改的权限
 * （5）port：端口过滤器，表示会验证通过了url配置的资源的请求的端口号，例：“/port.jsp=port[8088]”访问port.jsp时端口号不是8088会提示错误
 * （6）rest：restful类型过滤器，表示会对通过了url配置的资源进行restful风格检查，例：“/welcom=rest[user:create]”表示通过restful访问welcom资源时只有新增权限
 * （7）roles：角色过滤器，表示访问通过了url配置的资源会检查是否拥有该角色，例：“/welcom.jsp=roles[admin]”表示访问welcom.jsp页面时会检查是否拥有admin角色
 * （8）ssl：ssl过滤器，表示通过了url配置的资源只能通过https协议访问，例：“/welcom.jsp=ssl”表示访问welcom.jsp页面如果请求协议不是https会提示错误
 * （9）user：用户过滤器，表示可以使用登录验证/记住我的方式访问通过了url配置的资源，例：“/welcom.jsp=user”表示访问welcom.jsp页面可以通过登录验证或使用记住我后访问，否则直接跳转到登录
 * （10）logout：退出拦截器，表示执行logout方法后，跳转到通过了url配置的资源，例：“/logout.jsp=logout”表示执行了logout方法后直接跳转到logout.jsp页面
 */
@Configuration
public class ShiroConfig {

	//注入realm
	@Bean
	public AccountRealm accountRealm() {
		return new AccountRealm();
	}

	@Bean
	public RedisCacheManager redisCacheManager(
			@Qualifier("redisTemplate") RedisTemplate redisTemplate) {
		RedisCacheManager redisCacheManager = new RedisCacheManager();
		redisCacheManager.setCacheLive(120);
		redisCacheManager.setRedisTemplate(redisTemplate);
		return redisCacheManager;
	}

//    @Bean
//    public RedisManager getRedisManager(){
//        RedisManager redisManager = new RedisManager();
//        redisManager.setHost("172.16.50.100");
//        redisManager.setPort(6379);
//        redisManager.setDatabase(1);
//        return redisManager;
//    }
//    
//    @Bean
//    public RedisCacheManager getRedisCacheManager(){
//        RedisCacheManager redisCacheManager = new RedisCacheManager();
//        redisCacheManager.setRedisManager(getRedisManager());
//        redisCacheManager.setExpire(120);
//        return redisCacheManager;
//    } 


	/**
	 * 自定义sessionManager
	 *
	 * @return
	 */
	@Bean
	public DefaultWebSessionManager sessionManager() {
		DefaultWebSessionManager shiroSessionManager = new DefaultWebSessionManager();
		//删除失效session
		shiroSessionManager.setDeleteInvalidSessions(true);
		return shiroSessionManager;
	}

	//注入安全管理器
	@Bean
	public DefaultWebSecurityManager securityManager(
			@Qualifier("accountRealm") AccountRealm accountRealm,
	        @Qualifier("sessionManager") DefaultWebSessionManager sessionManager) {
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setRealm(accountRealm);
		manager.setSessionManager(sessionManager);
		return manager;
	}

	//注入工厂
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(
			@Qualifier("securityManager") DefaultWebSecurityManager securityManager,
	        @Qualifier("kickoutSessionControlFilter") KickoutSessionControlFilter kickoutSessionControlFilter) {
		ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
		factoryBean.setSecurityManager(securityManager);

		//自定义拦截器限制并发人数,参考博客
		LinkedHashMap<String, Filter> filtersMap = new LinkedHashMap<>();
		//限制同一帐号同时在线的个数
		filtersMap.put("kickout", kickoutSessionControlFilter);
		factoryBean.setFilters(filtersMap);

		//权限设置
		Map<String, String> map = new LinkedHashMap<>();
		map.put("/admin", "anon");
		map.put("/admin/login.do", "anon");
		map.put("/payRecord/pay.do", "anon");
		map.put("/admin/index.do", "kickout,authc");
		map.put("/view/list.do", "kickout,authc");
		map.put("/line/list.do", "kickout,authc");
		map.put("/order/manageList.do", "kickout,authc");
		map.put("/user/private/**", "kickout,authc");
		map.put("/leaveMessage/**", "kickout,authc");
		map.put("/payRecord/toPay.do", "kickout,authc");
		map.put("/order/**", "kickout,authc");
		map.put("/manage", "perms[manage]");
		map.put("/administrator", "roles[administrator]");
		factoryBean.setFilterChainDefinitionMap(map);
		//设置登陆页面
		factoryBean.setLoginUrl("/f_login.html");
		//设置未授权页面
		factoryBean.setUnauthorizedUrl("/unauth");
		return factoryBean;
	}

	/**
	 * 并发登录控制
	 *
	 * @return
	 */
	@Bean
	public KickoutSessionControlFilter kickoutSessionControlFilter(
			@Qualifier("sessionManager") DefaultWebSessionManager sessionManager,
			@Qualifier("redisCacheManager") RedisCacheManager redisCacheManager) {
		KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
		//用于根据会话ID，获取会话进行踢出操作的；
		kickoutSessionControlFilter.setSessionManager(sessionManager);
		//使用cacheManager获取相应的cache来缓存用户登录的会话；用于保存用户—会话之间的关系的；
		kickoutSessionControlFilter.setCacheManager(redisCacheManager);
		//是否踢出后来登录的，默认是false；即后者登录的用户踢出前者登录的用户；
		kickoutSessionControlFilter.setKickoutAfter(false);
		//同一个用户最大的会话数，默认1；比如2的意思是同一个用户允许最多同时两个人登录；
		kickoutSessionControlFilter.setMaxSession(1);
		//被踢出后重定向到的地址；
		kickoutSessionControlFilter.setKickoutUrl("/user/login.do?kickout=1");
		return kickoutSessionControlFilter;
	}

	//shiro整合thymeleaf,要想在html中使用shiro，就必须先引进方言。
	@Bean
	public ShiroDialect shiroDialect() {
		return new ShiroDialect();
	}
}
