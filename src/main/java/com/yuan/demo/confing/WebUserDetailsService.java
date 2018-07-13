package com.yuan.demo.confing;

import com.yuan.demo.sevice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WebUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.yuan.demo.model.User user = userService.getByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名或密码错误...");
        }
        return new User(user.getUsername(),user.getPassword(),true,true,true,true, getGrantedAuthrities());
    }

    /**
     * 添加授权
     * @return
     */
    private Collection<? extends GrantedAuthority> getGrantedAuthrities(){
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");

        grantedAuthorities.add(simpleGrantedAuthority);
        return grantedAuthorities;
    }
}
