package com.yangy.web.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yangy.web.constant.CommonConstants;
import com.yangy.web.entity.User;
import com.yangy.web.mapper.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author yangy
 * @description 这是shiro的认证和授权规则,必须继承 AuthorizingRealm类
 */
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;

    //先认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //客户端传过来的对象和密码，自动封装在token中，
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //根据用户名进行查询，并且判断
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("login_name", String.valueOf(token.getUsername())));
        if(Objects.nonNull(user)){
            //用户名不为空，继续验证密码
            return new SimpleAuthenticationInfo(user,user.getPassword(),getName());
        }
        
        //用户名为空，直接跳出验证
        return null;
    }

    //再授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取当前用户邓登陆的信息
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        //设置角色
        Set<String> roles = new HashSet<>();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if(Objects.nonNull(user)){
            if(user.getType().intValue() == CommonConstants.USER_TYPE_OF_TOURIST){
                roles.add("tourist");
                info.setRoles(roles);
                //设置权限
                info.addStringPermission("/delete");
            }else if(user.getType().intValue() == CommonConstants.USER_TYPE_OF_MANAGE){
                roles.add("manage");
                info.setRoles(roles);
            }
        }
        
        return info;
    }

}
