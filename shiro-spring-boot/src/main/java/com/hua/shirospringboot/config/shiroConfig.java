package com.hua.shirospringboot.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class shiroConfig {

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean() ;
        // 安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);

        // 内置过滤器
        /**
         * anon 无需认证
         * authc 认证才能用
         * user 记住我才能用
         * perms 拥有某个资源权限
         * role 拥有角色权限
         */

        Map<String ,String> filterMap = new LinkedHashMap<>() ;
//        filterMap.put("/user/add","authc");
//        filterMap.put("/user/update","authc");
        // 以字符串校验授权
        filterMap.put("/user/update","perms[user:update]");
        filterMap.put("/user/add","perms[user:add]");
        bean.setFilterChainDefinitionMap(filterMap);

        bean.setUnauthorizedUrl("/noauth");

        // 未认证登录
        bean.setLoginUrl("/toLogin");
        return  bean ;
    }





    // 安全管理
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager() ;

        // 管理realm
        securityManager.setRealm(userRealm);

        return securityManager ;
    }

    // 管理数据
    @Bean(name = "userRealm")
    public UserRealm userRealm(){
        return new UserRealm();
    }
}
