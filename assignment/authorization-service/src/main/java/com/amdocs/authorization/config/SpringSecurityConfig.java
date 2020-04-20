package com.amdocs.authorization.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.amdocs.authorization.controller.AuthorizationRestController;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(SpringSecurityConfig.class);
	
	@Autowired
    private DataSource dataSource;

    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
    
  
    
  /*   @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.h2.Driver");
        driverManagerDataSource.setUrl("jdbc:h2:file:~/test");
        driverManagerDataSource.setUsername("sa");
        driverManagerDataSource.setPassword("");
        return driverManagerDataSource;
    }*/
    
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.httpBasic()
            .and()
            .authorizeRequests()
            .antMatchers("/console/**").permitAll()
            .anyRequest().authenticated();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.jdbcAuthentication().dataSource(dataSource)
           .authoritiesByUsernameQuery("select USERNAME, ROLE from EMPLOYEE where USERNAME=?")
            .usersByUsernameQuery("select USERNAME, PASSWORD  from EMPLOYEE where USERNAME=?");
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	

	
	/*@Autowired
    DataSource datasource;
	
	@Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.h2.Driver");
        driverManagerDataSource.setUrl("jdbc:h2:file:~/test");
        driverManagerDataSource.setUsername("sa");
        driverManagerDataSource.setPassword("");
        return driverManagerDataSource;
    }
	
	
	@Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		logger.info("starting authentication");
		//username=? and 
		//username=? and
		auth.jdbcAuthentication().dataSource(datasource)
		.usersByUsernameQuery(
		           "select username,password from users where username=?")
		          .authoritiesByUsernameQuery(
		           "select username,role from authorities where username=?");
		;
		
		logger.info("DB Values is ::"+auth.jdbcAuthentication().dataSource(datasource)
		          .usersByUsernameQuery(
		        		  "select username,password from users where statusenable=true")
		                 .authoritiesByUsernameQuery(
		                		 "select username,role from authorities "));
    }
	
	 @Override
     protected void configure(HttpSecurity http) throws Exception {
		 logger.info("starting authorization");
        // http.authorizeRequests()
         //.antMatchers("/console/**").hasRole("ADMIN")
        // .antMatchers("/authorization/**").permitAll().anyRequest().authenticated();
         //.hasRole("ADMIN")
             //.anyRequest().authenticated();
             //.and().httpBasic();
           //  .and()
           //  .formLogin();
		 http.authorizeRequests()	        
	        .antMatchers(HttpMethod.POST,"/authorization/userprofiles").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')").anyRequest().authenticated()
	        .antMatchers(HttpMethod.PUT, "/authorization/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	        .antMatchers(HttpMethod.DELETE,"/authorization/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	        .anyRequest().authenticated()
	        .and()
            .csrf().disable()
            .formLogin().disable()
            ;
		 http.httpBasic()
         .and()
         .authorizeRequests()
         .anyRequest().authenticated();
     }
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/welcome").hasAnyRole("USER", "ADMIN")
				.antMatchers("/getEmployees").hasAnyRole("USER", "ADMIN").antMatchers("/addNewEmployee")
				.hasAnyRole("ADMIN").anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll()
				.and().logout().permitAll();

		http.csrf().disable();
	}

 */
}
