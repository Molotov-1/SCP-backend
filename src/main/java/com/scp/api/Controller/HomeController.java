package com.scp.api.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, String> inicio() {
        return Map.of(
                "mensaje", "API SCP funcionando",
                "endpointScps", "/api/v1/scps"
        );
    }
}
