package com.ksvsofttech.product.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
        
    @Autowired 
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
	 @Override
	 @Bean
	    public UserDetailsService userDetailsService() {
	        return new UserDetailsServiceImpl();
	    }
	
	@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return authProvider;
    }
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.and()
		.formLogin()
		.loginPage("/login").permitAll()
		.loginProcessingUrl("/loginSuccess").permitAll()
		.usernameParameter("loginId")
		.passwordParameter("password")
		.defaultSuccessUrl("/loginSuccess")
		.and()
		.logout().logoutUrl("/logout")
		.addLogoutHandler(new HeaderWriterLogoutHandler( new
		 ClearSiteDataHeaderWriter( ClearSiteDataHeaderWriter.Directive.CACHE,
	     ClearSiteDataHeaderWriter.Directive.COOKIES,
	     ClearSiteDataHeaderWriter.Directive.STORAGE)))
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
		.clearAuthentication(true)
		.invalidateHttpSession(true)
		.deleteCookies("JSESSIONID", "remember-me")
		.logoutSuccessUrl("/login?logout").permitAll()
		.and()
		.exceptionHandling()
		.accessDeniedPage("/error");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/scss/**", "/ksv/**", "/vendor/**");
	}
	
}
