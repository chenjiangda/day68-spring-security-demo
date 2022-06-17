package com.atguigu.service;

import com.atguigu.pojo.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Component("userService")  默认bean的id就是类名首字母小写
@Component
public class UserService implements UserDetailsService {


    //模拟数据库中的用户数据
    static Map<String, User> map =   new HashMap<String, User>();

    static {
        User user1 =  new User();
        user1.setUsername("admin");
        //明文密码
        //user1.setPassword("admin");
        //这是用BCrypt加密后的密码(密码明文是123) - 别忘了在spring-security.xml中配置密码加密策略
        user1.setPassword("$2a$10$IP3/o6kQ0DZxcPg87oCdw.k9CcFced9wHkvF8cB5xwwnH51UX5YY2");
        user1.setTelephone("123");

        User user2 =  new User();
        user2.setUsername("zhangsan");
        user2.setPassword("$2a$10$90obGBgo8YuF6GQyeveEhO/JI7ZQEplpDXocdhY4gG.y2dEXbGGHy");
        user2.setTelephone("321");

        map.put(user1.getUsername(),user1);
        map.put(user2.getUsername(),user2);
    }


    //框架帮我们认证时，调用这个方法，传递登录表单的用户名称(形参)，我们根据用户名称到数据库中查询用户信息，
    //将用户名称，密码，和权限集合返回给框架即可。
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //从数据库中查询用户信息
        User userInDb = map.get(username);

        if (userInDb==null){
            //根据用户名没有查询到用户，抛出异常，表示登录名输入有误

            //如果查不到这个用户名的用户框架会抛异常然后去往异常页面（spring-security.xml中配置的异常页面是登录页面）
            return null;
        }
        //模拟数据库中的密码，后期需要查询数据库
        //String passwordInDb ="{noop}" + userInDb.getPassword();
        //授权，后期需要改为查询数据库动态获得用户拥有的权限和角色，lists是一个权限的列表
        List<GrantedAuthority> lists = new ArrayList<>();
        //给登录用户分配下面三个权限
        lists.add(new SimpleGrantedAuthority("add"));
        lists.add(new SimpleGrantedAuthority("delete"));
        lists.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        //lists.add(new SimpleGrantedAuthority("ABC"));


        //下面这个User是框架提供的User，在一个文件中用到两个User类，其中一个需要用全类名加以区分
        // User  implements UserDetails
        //return new org.springframework.security.core.userdetails.User(userInDb.getUsername(),passwordInDb,lists);
        //直接把bcrypt的加密结果结果返回给框架，框架用密码编码器帮我们进行校验，别的不用我们管，我们只需要在配置文件中进行配置即可
        return new org.springframework.security.core.userdetails.User(userInDb.getUsername(),userInDb.getPassword(),lists);
        //方法的返回值是一个接口，实际返回的是他的实现类
    }
}
