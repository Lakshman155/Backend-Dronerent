package com.drone.drone.config;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.drone.drone.jwt.CustomFilter;

import com.drone.drone.services.MyUserDetailsService;
import com.drone.drone.services.UsersService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
@Autowired
private CustomFilter customfilter;
	
@Autowired
 private UserDetailsService myuserdetailsservice; 


	@Bean 
	public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
		
		
		
		 http
	            .csrf(csrf -> csrf.disable()) 
	            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
	            .authorizeHttpRequests(authorize -> 
	                authorize
	                    .requestMatchers("/users/login","/users/adduser","/products/getproducts","/").permitAll()  
	                    .anyRequest().authenticated()                 
	            )
//	            .httpBasic(Customizer.withDefaults())
	            .sessionManagement(session ->
	                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	            )
	            
	            .authenticationProvider(authenticationProvider())
	            .addFilterBefore(customfilter,UsernamePasswordAuthenticationFilter.class)
	            ;
	              

	        return http.build();
	}
	
	

	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000")); 
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE")); 
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type")); 
        configuration.setAllowCredentials(true); // Allow credentials

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
	
	
	


	
	
	@Bean
	public BCryptPasswordEncoder getBcryptpasswordencode() {
		return new BCryptPasswordEncoder(12);
	}
	

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(myuserdetailsservice);

        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        return authenticationProvider;
    }
    
    
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();

    }
	

	public SecurityConfig() {
		
		
		
		// TODO Auto-generated constructor stub
	}

}
