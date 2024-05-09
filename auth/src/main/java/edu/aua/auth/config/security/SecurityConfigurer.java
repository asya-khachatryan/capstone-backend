package edu.aua.auth.config.security;

import edu.aua.auth.service.JWTUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfigurer {
    private static final String LOGIN_ENDPOINT = "/auth/**";
    private static final String REGISTER_ENDPOINT = "/user/register";
    private static final String ADMIN_ENDPOINT = "/admin/**";


    @Autowired
    private JWTUserDetailService jwtUserDetailService;

    public static List<String> getAllowedOrigins() {
        String[] hosts = new String[]{"localhost"};
        String[] prefixes = new String[]{"http://", "https://", "http://www.", "https://www."};
        String[] ports = new String[]{"8080/", "3000/", "8093/", "8080", "3000", "8093"};
        ArrayList<String> origins = new ArrayList<>();
        for (String host : hosts) {
            for (String prefix : prefixes) {
                origins.add(prefix + host);
                for (String port : ports) {
                    origins.add(prefix + host + ":" + port);
                }
            }
        }
        return origins;
    }


    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(jwtUserDetailService).passwordEncoder(passwordEncoder());
        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

        CorsConfiguration config = new CorsConfiguration();
        for (String origin : getAllowedOrigins()) {
            config.addAllowedOrigin(origin);
        }
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);
        config.setAllowedMethods(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        http
                .headers(headers -> headers
                        .addHeaderWriter((a, b) -> {
                            b.setHeader("Access-Control-Allow-Credentials", "true");
                        }))
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(source))
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .logout(logout -> logout.logoutUrl("/logout").deleteCookies("JSESSIONID", "accessToken")
                        .logoutSuccessHandler((req, response, auth) -> response.setStatus(200)).permitAll(false)
                )
                .authorizeHttpRequests(authReq -> authReq.requestMatchers(LOGIN_ENDPOINT).permitAll())
                .authorizeHttpRequests(authReq -> authReq.requestMatchers(REGISTER_ENDPOINT).permitAll())
//                .authorizeHttpRequests(authReq -> authReq.requestMatchers(ADMIN_ENDPOINT).hasRole("ADMIN"))
                .authorizeHttpRequests(authReq -> authReq.anyRequest().permitAll())
                .authenticationManager(authenticationManager);

        http.addFilterBefore(tokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(HttpMethod.POST, LOGIN_ENDPOINT, ADMIN_ENDPOINT);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public JWTTokenFilter tokenFilter() {
        return new JWTTokenFilter();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
