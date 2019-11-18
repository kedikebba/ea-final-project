package ea.finalproject.uiservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private JwtRequestFilter filter;
////    @Autowired
////    private UserFilter userFilter;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userService);
//    }
//
////    @Bean
////    public FilterRegistrationBean filterRegistrationBean(){
////        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
////        filterRegistrationBean.setFilter(userFilter);
////        filterRegistrationBean.addUrlPatterns("/url/*");
////        filterRegistrationBean.setOrder(1);
////        return filterRegistrationBean;
////    }
//
//   @Bean
//   public PasswordEncoder passwordEncoder(){
//       return NoOpPasswordEncoder.getInstance();
//   }
//
@Bean
@Override
public UserDetailsService userDetailsService() {
    UserDetails user =
            User.withDefaultPasswordEncoder()
                    .username("user")
                    .password("password")
                    .roles("USER")
                    .build();

    return new InMemoryUserDetailsManager(user);
}

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/login","/","/index").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/index")
                .failureUrl("/login?error")
//                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
//                .permitAll()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }

}
