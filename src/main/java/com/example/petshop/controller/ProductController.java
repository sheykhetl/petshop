package com.example.petshop.controller;

import com.example.petshop.dto.ProductCategory;
import com.example.petshop.entity.Category;
import com.example.petshop.entity.Product;
import com.example.petshop.entity.User;
import com.example.petshop.service.ProductService;
import com.example.petshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String index(Model model, Principal principal) {
        List<Product> products = productService.getProducts();
        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product";
    }

    @GetMapping("/product/{id}/buy")
    public String processProduct(@PathVariable Long id, Principal principal) {
        Product product = productService.getProductById(id);
        User user = userService.getUserByPrincipalName(principal.getName());
        userService.buyProduct(user, product);
        return "redirect:/profile";
    }

    @PostMapping("/product/create")
    public String createProduct(ProductCategory productCategory, Principal principal) {
        productService.createProduct(productCategory);
        return "redirect:/profile";
    }

    @PostMapping("/category/create")
    public String createCategory(Category category, Principal principal) {
        productService.createCategory(category);
        return "redirect:/profile";
    }
}
