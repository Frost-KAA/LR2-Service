package com.example.lr3auth2

import com.example.lr3auth2.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer.AuthorizationManagerRequestMatcherRegistry
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityConfig(userService: UserService) {
    private lateinit var userService: UserService

    init {
        this.userService = userService
    }

    @Autowired
    @Throws(Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService<UserDetailsService>(userService).passwordEncoder(passwordEncoder())
    }

    @Bean
    @Throws(Exception::class)
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity.headers().frameOptions().sameOrigin()
        return httpSecurity.csrf().disable()
            .cors().disable().formLogin()
            .and()
            .authorizeHttpRequests(Customizer { authorizationManagerRequestMatcherRegistry ->
                authorizationManagerRequestMatcherRegistry
                    .antMatchers(HttpMethod.GET, "/", "/api/login", "/api/logout")
                    .permitAll()
                    .antMatchers(HttpMethod.GET, "/admin").hasRole("ADMIN")
                    .antMatchers(HttpMethod.GET, "/user").hasRole("USER")
            }).httpBasic(Customizer.withDefaults()).build()
    }

    companion object {
        @Bean
        fun passwordEncoder(): BCryptPasswordEncoder {
            return BCryptPasswordEncoder()
        }
    }
}