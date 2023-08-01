package com.example.ecommerce.pages;

import com.example.ecommerce.configs.ApiKeyNotRequired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@ApiKeyNotRequired
@Controller
public class PageController {
    
    @GetMapping("/")
    public String index() {
        return "index.html";
    }
}
