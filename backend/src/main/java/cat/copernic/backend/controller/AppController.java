package cat.copernic.backend.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class AppController {
    
    @GetMapping("/test")
    public String getMethodName() {
        return "<h1>Hello, World!</h1>";
    }
    
}