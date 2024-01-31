package com.codewithtechsagar.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.codewithtechsagar.blog.security.CustomUserDetailService;
import com.codewithtechsagar.blog.security.JwtAuthenticationEntryPoint;
import com.codewithtechsagar.blog.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	
	public static final String[] PUBLIC_URLS= {
			"/auth/**",
			"/v3/api-docs",
			"/v2/api-docs",
			"/swagger-resources/",
			"/swagger-ui/**",
			"/webjars/**"
	};

	@Autowired
	private JwtAuthenticationEntryPoint point;

	@Autowired
	private JwtAuthenticationFilter filter;
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	//WebSecurityConfigurationAdapter is Depricated

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable()).authorizeRequests().requestMatchers("/test").authenticated()
				.requestMatchers(PUBLIC_URLS).permitAll()
				.requestMatchers(HttpMethod.GET).permitAll()
				.anyRequest().authenticated().and()
				.exceptionHandling(ex -> ex.authenticationEntryPoint(point))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		http.authenticationProvider(daoAuthenticationProvider());
		return http.build();

	}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
    
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
    	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    	provider.setUserDetailsService(this.customUserDetailService);
    	provider.setPasswordEncoder(passwordEncoder());
    	
    	return provider;
    }
}
