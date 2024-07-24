package com.bhavan.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FlipkartWebController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login() {
        // Handle login logic here
        return "redirect:/"; // Redirect to home or dashboard
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register() {
        // Handle registration logic here
        return "redirect:/login"; // Redirect to login page after registration
    }

    // Forgot Password Page
    @GetMapping("/forgot")
    public String forgotPasswordPage() {
        return "forgot";
    }

    // Password Reset Form Submission
    @PostMapping("/reset-password")
    public String resetPasswordSubmit() {
        // Implement password reset logic here
        return "redirect:/login"; // Redirect to login page after successful password reset
    }

    // Dashboard Page
    @GetMapping("/dashboard")
    public String dashboardPage() {
        return "dashboard"; // Thymeleaf template for dashboard
    }

    @GetMapping("/orders")
    public String orders() {
        return "orders";
    }

    @GetMapping("/products")
    public String products() {
        return "products";
    }
}
