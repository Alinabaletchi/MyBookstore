package org.example.library.config;


import org.example.library.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/api/auth/**").permitAll();
                    auth.requestMatchers("/api/auth/login").permitAll();
                    auth.requestMatchers("/api/register/**").permitAll();
                    auth.requestMatchers("/api/register").permitAll();
                    auth.requestMatchers("/api/users/**").hasRole("ADMIN");
                    auth.requestMatchers("/api/users").permitAll();
                    auth.requestMatchers("/api/carts/**").permitAll();
                    auth.requestMatchers("/api/carts/mycart").authenticated();
                    auth.requestMatchers("/api/carts/addBookToCart").authenticated();
                    auth.requestMatchers("/api/carts/addGameToCart").authenticated();
                    auth.requestMatchers("/api/carts/addGameToCart").authenticated();
                    auth.requestMatchers("/api/carts/addProductToCart").authenticated();
                    auth.requestMatchers("/api/wishlist/**").permitAll();
                    auth.requestMatchers("/api/wishlist/mywishlist").authenticated();
                    auth.requestMatchers("/api/wishlist/addBookToWishlist").authenticated();
                    auth.requestMatchers("/api/wishlist/addGameToWishlist").authenticated();
                    auth.requestMatchers("/api/wishlist/addProductToWishlist").authenticated();
                    auth.requestMatchers("/api/books/**").permitAll();
                    auth.requestMatchers("/api/books/{type}").permitAll();
                    auth.requestMatchers("/api/books/allBooks").permitAll();
                    auth.requestMatchers("/api/books/createBooks").hasRole("ADMIN");
                    auth.requestMatchers("/api/games/**").permitAll();
                    auth.requestMatchers("/api/games/allGames").permitAll();
                    auth.requestMatchers("/api/games/createGames").hasRole("ADMIN");
                    auth.requestMatchers("/api/products/**").permitAll();
                    auth.requestMatchers("/api/products/allProducts").permitAll();
                    auth.requestMatchers("/api/products/createProducts").hasRole("ADMIN");
                    auth.anyRequest().authenticated();
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:4200");
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT","PATCH", "DELETE"));
        configuration.setAllowCredentials(true);
        configuration.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager customAuthenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class).build();
    }
}

