package agency.caragency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories("agency.caragency.repository")
@EntityScan("agency.caragency.model")
@ComponentScan("agency.caragency")
public class CarAgencyApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(CarAgencyApplication.class, args);
    }
}