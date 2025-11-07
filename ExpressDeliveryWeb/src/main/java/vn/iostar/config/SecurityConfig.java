package vn.iostar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        System.out.println("JwtAuthenticationFilter initialized!");
    }


    // Cấu hình loại bỏ một số yêu cầu xác thực cho các file tĩnh
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().requestMatchers(HttpMethod.OPTIONS, "/**");
//    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(
//                request -> request.requestMatchers( "/home/**").permitAll()
//                                            .requestMatchers("/account_handle/**", "/payment/**", "/api/**", "/account/**").hasAuthority("ROLE_USER")
//                                            .requestMatchers("/ws/**").permitAll()
//                                            .anyRequest()
//                                            .authenticated()
//                            ).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
                    http.headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
                    );
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
}

    @Bean
        public PasswordEncoder passwordEncoder (){
            return new BCryptPasswordEncoder(10 );
        }
}