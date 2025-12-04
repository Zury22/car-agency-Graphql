package agency.caragency.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
@ConfigurationProperties(prefix = "application.security.jwt")
public class JwtProperties {

    /**
     * Clave secreta utilizada para firmar y verificar los JWT.
     */
    private String secretKey;

    /**
     * Tiempo de expiración del access token.
     */
    private Duration expiration;

    /**
     * Tiempo de expiración del refresh token.
     */
    private Duration refreshTokenExpiration;

    // Getters y Setters
    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Duration getExpiration() {
        return expiration;
    }

    public void setExpiration(Duration expiration) {
        this.expiration = expiration;
    }

    public Duration getRefreshTokenExpiration() {
        return refreshTokenExpiration;
    }

    public void setRefreshTokenExpiration(Duration refreshTokenExpiration) {
        this.refreshTokenExpiration = refreshTokenExpiration;
    }
}