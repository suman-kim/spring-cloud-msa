package org.example.firstservice;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/first-service")
@Slf4j
public class FirstServiceController {

    Environment env;

    public FirstServiceController(Environment env) {
        this.env = env;
    }


    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the First Service";
    }


    @GetMapping("/check")
    public String check(HttpServletRequest request) {
        log.info("Server port={}", request.getServerPort());
        return String.format("Hi, I'm the First Service %s", env.getProperty("local.server.port"));
    }
}
