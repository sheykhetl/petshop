package com.example.petshop.service;

import com.example.petshop.entity.Product;
import com.example.petshop.entity.User;
import com.example.petshop.enums.Role;
import com.example.petshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public boolean createUser(User user) {
        String email = user.getEmail();

        if (userRepository.findByEmail(email) != null) {
            return false;
        }
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        userRepository.save(user);
        return true;
    }

    public List<User> list() {
        return userRepository.findAll();
    }

    public User getUserByPrincipalName(String name) {
        return userRepository.findByEmail(name);
    }

    public void buyProduct(User user, Product product) {
        user.getProducts().add(product);
        userRepository.save(user);
    }

    public Set<Product> getUserProducts(String name) {
        return userRepository.findByEmail(name).getProducts();
    }
}
