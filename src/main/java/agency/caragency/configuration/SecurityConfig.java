package agency.caragency.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    
    @Value("${swagger.username}")
    private String swaggerUsername;
    
    @Value("${swagger.password}")
    private String swaggerPassword;
    
    // Chain 1: HTTP Basic para Swagger y GraphQL (prioridad alta)
    @Bean
    @Order(1)
    public SecurityFilterChain swaggerSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .securityMatcher(
                "/swagger-ui/**",
                "/swagger-ui.html",
                "/v3/api-docs/**",
                "/graphql",
                "/graphiql/**",
                "/graphiql"
            )
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults())
            .authenticationProvider(swaggerAuthenticationProvider())
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .maximumSessions(1)
            );
        
        return http.build();
    }
    
    // Chain 2: JWT para el resto de la API
    @Bean
    @Order(2)
    public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorize -> authorize
                // Endpoints públicos
                .requestMatchers("/api/auth/**", "/error", "/actuator/**").permitAll()
                
                // Todo lo demás requiere JWT
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            // Solo agregar filtro JWT en esta chain
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    
    // AuthenticationProvider específico para Swagger
    @Bean
    public AuthenticationProvider swaggerAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(swaggerUserDetailsService());
        provider.setPasswordEncoder(noOpPasswordEncoder());
        return provider;
    }
    
    // PasswordEncoder sin encriptación para Swagger
    public PasswordEncoder noOpPasswordEncoder() {
        return org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    // Usuario en memoria SOLO para Swagger/GraphQL
    public UserDetailsService swaggerUserDetailsService() {
        System.out.println("=== SWAGGER AUTH CONFIG ===");
        System.out.println("Username: " + swaggerUsername);
        System.out.println("Password: " + swaggerPassword);
        System.out.println("===========================");
        
        UserDetails swaggerUser = User.builder()
                .username(swaggerUsername)
                .password(swaggerPassword) // Sin prefijo {noop}
                .roles("SWAGGER_USER")
                .build();
        
        return new InMemoryUserDetailsManager(swaggerUser);
    }
}