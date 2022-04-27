package com.example.demo;

import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userService;
    private final DataSource dataSource;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/anonymous*").anonymous()
                .antMatchers("/user/login*").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().successHandler(successHandler)
                .usernameParameter("login").passwordParameter("password")
                .successForwardUrl("/user/welcomePage")
                .loginPage("/user/login")
                .loginProcessingUrl("/user/login")
                .defaultSuccessUrl("/user/welcomePage", true)
                .failureUrl("/login.html?error=true")
                .and()
                .logout()
                .logoutUrl("/perform_logout")
                .deleteCookies("JSESSIONID")
                .permitAll()
                .logoutSuccessUrl("/");

//        http.csrf()
//                .disable()
//                .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/favicon.ico").permitAll()
//                .antMatchers("/css/**").permitAll()
//                .antMatchers("/js/**").permitAll()
//                .antMatchers("/static/**").permitAll()
//                .antMatchers("/fonts/**").permitAll()
//                .antMatchers("/vendor/**").permitAll()
//                .antMatchers("/images/**").anonymous()
//                .antMatchers("/blog/**").permitAll()
//                .antMatchers("/user/registration").not().fullyAuthenticated()
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/course").hasRole("STUDENT")
//                .antMatchers("/user/welcomePage").hasRole("STUDENT")
//                .antMatchers("/personalInform").hasRole("STUDENT")
//                .antMatchers("/", "/resources/**").anonymous()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().successHandler(successHandler)
//                .loginPage("/user/login")
//                .usernameParameter("login").passwordParameter("password")
//                .successForwardUrl("/personalInform")
//                .defaultSuccessUrl("/")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll()
//                .logoutSuccessUrl("/");
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder);

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    private final AuthenticationSuccessHandler successHandler = (request, response, auth) -> {
//            HttpSession session = request.getSession();
//            UserDetails userDetails = (UserDetails) auth.getPrincipal();
//            User user = userService.getByLogin(userDetails.getUsername());

            response.setStatus(HttpServletResponse.SC_OK);
            String url = request.getContextPath() + "/personalInform";
            response.sendRedirect(url);
    };
}
