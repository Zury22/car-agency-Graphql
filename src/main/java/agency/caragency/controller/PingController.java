package agency.caragency.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Ping", description = "Checks if the API is up and running")
@RestController
@RequestMapping("/api/ping")
public class PingController {

    @Operation(summary = "Connectivity check", description = "Returns 'pong' to confirm the API is active")
    @GetMapping
    public String ping() {
        return "pong";
    }
}
