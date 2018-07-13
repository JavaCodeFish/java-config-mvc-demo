package com.yuan.demo.confing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public WebUserDetailsService webUserDetailsService(){
        return new WebUserDetailsService();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public WebAuthenticationSuccessHandler webAuthenticationSuccessHandler(){
        return new WebAuthenticationSuccessHandler();
    }
    @Bean
    public WebAuthenticationFailureHandler webAuthenticationFailureHandler(){
        return new WebAuthenticationFailureHandler();
    }

    /*@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("user").roles("USER").and()
                .withUser("user2").password("user2").roles("USER").and()
                .withUser("admin").password("admin").roles("ADMIN");
    }*/
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(webUserDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    /*protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated().and()
                .formLogin().and() // java配置使用and()方法相当于XML标签的关闭。
                .httpBasic();
    }*/

    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // csrf不关掉, 下面配置的请求权限会出问题
                .authorizeRequests()
                .mvcMatchers("/api/**","/assets/**", "/login").permitAll()
                .mvcMatchers("/admin/**").hasRole("ADMIN")
                .mvcMatchers("/download/**").hasRole("USER")
                .anyRequest().authenticated().and()
                .formLogin()
                    .loginPage("/login") // 指定登录页路径
                    .permitAll() // 我们必须允许所有用户访问我们的登录页（例如为验证的用户），这个formLogin().permitAll()方法允许基于表单登录的所有的URL的所有用户的访问。
                .successHandler(webAuthenticationSuccessHandler())
                .failureHandler(webAuthenticationFailureHandler());
    }
}
