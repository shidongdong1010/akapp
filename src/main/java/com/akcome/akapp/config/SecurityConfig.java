package com.akcome.akapp.config;

import com.akcome.akapp.security.CustomAuthenticationProvider;
import com.akcome.akapp.security.LoginSuccessHandler;
import com.akcome.akapp.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.sql.DataSource;

/**
 * security 配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;//code1

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 设置不拦截规则
        web.ignoring().antMatchers("/js/**", "/css/**", "/assets/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 关闭csrf
                .csrf().disable()
                //允许所有用户访问”/”和”/home”
                .authorizeRequests().antMatchers("/", "/home").permitAll()
                //其他地址的访问均需验证权限
                .anyRequest().authenticated()
                .and()
                .formLogin()
                        //指定登录页是”/login”
                .loginPage("/login")
                .permitAll()
                        //登录成功后可使用loginSuccessHandler()存储用户信息，可选。
                .successHandler(loginSuccessHandler())//code3
                .and()
                .logout()
                        //退出登录url”/logout”
                .logoutUrl("/logout")
                        //退出登录后的默认网址是”/login”
                .logoutSuccessUrl("/login")
                .permitAll()
                .invalidateHttpSession(true)
                .and()
                        //登录后记住用户，下次自动登录
                        //数据库中必须存在名为persistent_logins的表
                        //建表语句见code15
                .rememberMe()
                .tokenValiditySeconds(1209600)
                        //指定记住登录信息所使用的数据源
                .tokenRepository(tokenRepository());//code4

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // 不删除凭据，以便记住用户
        auth.eraseCredentials(false);

        auth.userDetailsService(customUserDetailsService);
        //
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        // 需要将密码加密后写入数据库
        //DaoAuthenticationProvider authenticationProvider =  new DaoAuthenticationProvider();
        CustomAuthenticationProvider authenticationProvider = new CustomAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        // 不隐藏找不到用户异常
        authenticationProvider.setHideUserNotFoundExceptions(false);
        // 指定密码加密所使用的加密器为passwordEncoder()
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    // Code5----------------------------------------------
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

    // Code4----------------------------------------------
    @Bean
    public JdbcTokenRepositoryImpl tokenRepository() {
        JdbcTokenRepositoryImpl j = new JdbcTokenRepositoryImpl();
        j.setDataSource(dataSource);
        return j;
    }

    // Code3----------------------------------------------
    @Bean
    public LoginSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler();//code6
    }
}