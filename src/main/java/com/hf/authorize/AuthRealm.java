package com.hf.authorize;

import com.hf.dto.Module;
import com.hf.dto.Role;
import com.hf.dto.User;
import com.hf.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Realm域，Shiro从从Realm获取安全数据
 *
 * Created by fjm on 2017/12/25.
 */
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;

    //授权,赋予权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) principalCollection.fromRealm(this.getClass().getName()).iterator().next();//获取session中的用户
        List<String> permissions = new ArrayList<>();
        Set<Role> roles = user.getRoles();
        if (roles.size() > 0) {
            for(Role role : roles) {
                Set<Module> modules = role.getModules();
                if(modules.size()>0) {
                    for(Module module : modules) {
                        permissions.add(module.getMname());
                    }
                }
            }
        }
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.addStringPermissions(permissions);//将权限放入shiro中.
        return info;
    }


    //认证.登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;//获取用户输入的token
        String username = usernamePasswordToken.getUsername();
        User user = userService.findByUserName(username);//根据用户名,获取对应的权限和密码
        return new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getName());
    }
}
