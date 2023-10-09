package com.mercury.finalProject.security;

import com.mercury.finalProject.security.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Autowired
    private AuthenticationEntryPointImpl authenticationEntryPointImpl;

    @Autowired
    private AccessDeniedHandlerImpl accessDeniedHandlerImpl;

    @Autowired
    private AuthenticationSuccessHandlerImpl authenticationSuccessHandlerImpl;

    @Autowired
    private AuthenticationFailureHandlerImpl authenticationFailureHandlerImpl;

    @Autowired
    private LogoutSuccessHandlerImpl logoutSuccessHandlerImpl;

    @Autowired
    private UserDetailsService userDetailsServiceImpl;

    //put the return into the spring bean container, as a bean, function name is the bean name
    //@Bean cannot put on the class
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors();

        http.authorizeRequests((requests) ->
                        requests
//                        .antMatchers(HttpMethod.GET, "/orders")
////                         .hasRole("USER")  //we do not usually use this in the future
//                        //We usually use this in the future
//                        .hasAuthority("ROLE_ADMIN")
//
//                        .antMatchers(HttpMethod.GET, "/orders/*")
//                        .hasAnyRole("ADMIN", "USER")

                                //.anyRequest().authenticated()
                                .anyRequest().permitAll()
        );

        http.exceptionHandling()
                .accessDeniedHandler(accessDeniedHandlerImpl)

                //!!!IMPORTANT.
                // With this line, we are not going to see our login page. We use front-end and postman instead.
                .authenticationEntryPoint(authenticationEntryPointImpl);

        http.formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(authenticationSuccessHandlerImpl)
                .failureHandler(authenticationFailureHandlerImpl)
        ;

        http.logout()
                .permitAll()
                .logoutUrl("/logout")
                .logoutSuccessHandler(logoutSuccessHandlerImpl)
        ;

        http.httpBasic();
        return http.build();
    }

    @Bean // put the return object into spring container, as a bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Autowired // @Autowired on function will autowired the parameters
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(new BCryptPasswordEncoder(11));
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        //configuration.addAllowedOrigin(""); // You should only set trusted site here. e.g. http://localhost:4200/ means only this site can access.
//        configuration.addAllowedOrigin("http://localhost:4200/"); // You should only set trusted site here. e.g. http://localhost:4200/ means only this site can access.

        configuration.addAllowedOrigin("http://localhost:4200");

//        configuration.addAllowedOrigin("https://aws-yining-frontend-demo.s3-website-us-west-1.amazonaws.com");
//        configuration.addAllowedOriginPattern("*");


        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","HEAD","OPTIONS"));

        //configuration.addAllowedHeader("");
        configuration.addAllowedHeader("*");

        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public HttpFirewall httpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedDoubleSlash(true); // Allow double slashes in the URL
        return firewall;
    }
}

