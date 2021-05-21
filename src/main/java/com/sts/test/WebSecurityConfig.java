package com.sts.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
	  
	  http
	    .authorizeRequests()
	      .antMatchers("/css/**").permitAll()
	      .antMatchers("/test").permitAll()
	      .anyRequest().authenticated()  // その他URLは、認証が必要。認証済みではないとアクセスできない
	      .and()
	    .formLogin()
	      .loginPage("/login") // ログイン画面もアクセス制限なし
	      .defaultSuccessUrl("/hello")
	      .permitAll() // 認証は不要
	      .and()
	    .logout()
	      .permitAll() // 認証は不要
	      .and()
        .httpBasic();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    String password = passwordEncoder().encode("123");

    auth.inMemoryAuthentication()
    	.passwordEncoder(passwordEncoder())
    	.withUser("wei").password(password).roles("ADMIN");

  }

  @Bean
  public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }

}