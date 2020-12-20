package com.hua.shirospringboot.config;


import com.hua.shirospringboot.pojo.userInfo;
import com.hua.shirospringboot.service.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserServiceImpl userService ;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权信息");


        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo( );
        //info.addStringPermission("user:add");
        Subject subject = SecurityUtils.getSubject() ;
        System.out.println(subject.getPrincipal());
        userInfo userInfo = (userInfo)subject.getPrincipal() ;
        info.addStringPermission(userInfo.getPerms());
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("认证身份");
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        userInfo userInfo = userService.getUserByName(usernamePasswordToken.getUsername()) ;
        if(userInfo==null){
            return null ; // 自动抛出name异常或者password异常 智能认证功能
        }
        return new SimpleAuthenticationInfo("",userInfo.getPassword(),"");
    }
}
