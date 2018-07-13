//package com.yuan.demo.confing;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@EnableWebSecurity
//public class MultiHttpSecurityConfig {
//
//    @Bean
//    public WebUserDetailsService webUserDetailsService(){
//        return new WebUserDetailsService();
//    }
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(webUserDetailsService())
//                .passwordEncoder(passwordEncoder());
//    }
//
//    @Configuration
//    @Order(1)
//    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
//        protected void configure(HttpSecurity http) throws Exception {
//            http
//                    .antMatcher("/api/**")
//                    .authorizeRequests()
//                    .anyRequest()
//                    .permitAll()
//                    .and()
//                    .httpBasic();
//        }
//    }
//
//    @Configuration
//    public static class WebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http
//                    .csrf().disable() // csrf不关掉, 下面配置的请求权限会出问题
//                    .authorizeRequests()
//                    .mvcMatchers("/assets/**", "/login").permitAll()
//                    .mvcMatchers("/admin/**").hasRole("ADMIN") // 会自动添加 ROLE_ 前缀, 所以角色名是 ROLE_ADMIN
//                    .mvcMatchers("/download/**").hasRole("USER")
//                    .anyRequest().authenticated().and()
//                    .formLogin()
//                    .loginPage("/login") // 指定登录页路径
//                    .permitAll(); // 我们必须允许所有用户访问我们的登录页（例如为验证的用户），这个formLogin().permitAll()方法允许基于表单登录的所有的URL的所有用户的访问。
//        }
//    }
//
//}
