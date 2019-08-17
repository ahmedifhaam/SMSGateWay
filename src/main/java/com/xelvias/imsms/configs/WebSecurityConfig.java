package com.xelvias.imsms.configs;

import com.xelvias.imsms.Models.Constants;
import com.xelvias.imsms.Models.Responses.LoginResponse;
import com.xelvias.imsms.Models.Responses.BaseResponse;
import com.xelvias.imsms.services.UserService;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private DataSource dataSource;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);

        userService.setbCryptPasswordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/anonymous*").anonymous()
                .antMatchers("/assets/*").permitAll()
                .antMatchers("/index.html").permitAll()
                .antMatchers("/*.js*").permitAll()
                .antMatchers("/*.ico").permitAll()
                .antMatchers("/*.png").permitAll()
                .antMatchers(HttpMethod.GET,"/api/user").permitAll()
                .antMatchers("/api/user").hasAuthority("SADMIN")
                .antMatchers("/api/allusers").hasAuthority("ADMIN")
                .antMatchers("/api/cuser").hasAuthority("USER")
                .antMatchers("/api/filefiltermessagelogs").hasAuthority("ADMIN")
                .antMatchers("/api/filemessagelogs").hasAuthority("ADMIN")
                .antMatchers("/api/messagelogs").hasAuthority("ADMIN")
                .antMatchers("/api/samplecsv").hasAuthority("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/index.html")
                .loginProcessingUrl("/perform_login")
                .permitAll()
                .successHandler(new SimpleUrlAuthenticationSuccessHandler(){
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//                        super.onAuthenticationSuccess(request, response, authentication);
                        clearAuthenticationAttributes(request);
                        LoginResponse loginResponse = new LoginResponse();
                        loginResponse.setResponse(Constants.SUCCESS);
                        loginResponse.setUser((User) authentication.getPrincipal());
                        response.getWriter().write(loginResponse.toString());
                    }
                })
                .failureHandler(new SimpleUrlAuthenticationFailureHandler(){
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//                        super.onAuthenticationFailure(request, response, exception);
                        LoginResponse loginResponse = new LoginResponse();
                        loginResponse.setResponse((Constants.FAILED));
                        loginResponse.setUser(null);
                        response.getWriter().write(loginResponse.toString());
                    }
                })
                .and()
                .logout()
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        BaseResponse baseResponse = new BaseResponse();
                        baseResponse.setResponse(Constants.SUCCESS);
                        response.getWriter().write(baseResponse.toString());
                    }
                })
                .logoutUrl("/perform_logout")
                .permitAll()
                .deleteCookies("JSESSIONID");
    }

//    @Bean
//    @Override
//    public UserDetailsService userDetailsService(){
//        UserDetails user.ts =
//                User.withDefaultPasswordEncoder().username("user.ts").password("pass").roles("USER").build();
////        return  new InMemoryUserDetailsManager(user.ts);
//
//    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

}
