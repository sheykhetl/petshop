package com.example.petshop.controller;

import com.example.petshop.entity.Product;
import com.example.petshop.entity.User;
import com.example.petshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Set;

@Controller
public class ProfileController {
    @Autowired
    UserService userService;

    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        Set<Product> products = userService.getUserProducts(principal.getName());
        User user = userService.getUserByPrincipalName(principal.getName());
        model.addAttribute("id", user.getId()   );
        model.addAttribute("email", user.getEmail());
        model.addAttribute("createdAt", user.getDateOfCreated());
        model.addAttribute("role", user.getRoles());
        model.addAttribute("products", products);
        return "profile";
    }
}
