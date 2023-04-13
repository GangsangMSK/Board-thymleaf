package iducs.springboot.boardkms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
    @GetMapping("/")
    public String goAdmin(){return "redirect:/admin/";}
}
