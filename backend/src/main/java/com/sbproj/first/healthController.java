//package backend.src.main.java.com.sbproj.first;
package com.sbproj.first;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class healthController {

    @GetMapping("/api/health") 
    public String health() {
        return "OK";
    }

    @GetMapping("/api/listings") 
    public String listings() {
        return "";
    }
}