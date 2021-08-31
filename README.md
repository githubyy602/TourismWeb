#序言
- 项目的基础框架：后端是`SpringBoot`，前端是`layui`，恳求各位大佬一个`star`和`fork`吧！也希望收到指点，与帮助:heart_eyes:！！
- 目前集成了如下组件（完善中~）：
1. `MybatisPlus`：根据表，自动生成代码，简单的sql语句不用写，不过复杂的多表查询还是要自己动手的。
2. `Druid`数据源：安全可靠，还有可视化界面。
3. `Redis`缓存：需要在本地开启`redis`。
4. `Shiro`安全框架：更轻量，更简单。

#项目结构

```
com
    └─yangy.web
        └─springboot
            │  SpringbootApplication.java  #启动类
            │
            ├─config
            │      AutoCode.java        #MybatisPlus自动生成代码的类
            │      DruidConfig.java     #Druid数据源配置文件
            │      RedisConfig.java     #redis序列化配置模板
            │      ShiroConfig.java     #shiro配置文件
            │      SwaggerConfig.java   #Swagger配置文件
            ├─realm  #shiro的认证和授权规则
            │      AccountRealm.java          
            │
            └─utils  #工具类
                    SendMailUtil.java #发送邮件的工具类
```

#下述配置静态资源可用
- :spring
    resources:
        static-locations: classpath:/META-INF/resources/,classpath:/resources/,classpath:/static/

# 管理员后台
http://localhost:9001/admin
账号密码：shenmei/shenmei

# 游客客户web登录页
http://localhost:9001/user

# shiro中文API文档
https://www.w3cschool.cn/shiro/
shiro控制并发在线人数：ShiroConfig、KickoutSessionControlFilter