package com.bhavan.controller;


import com.bhavan.dto.admin.request.AdminRequest;
import com.bhavan.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AdminWebController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
//    public String login(@RequestBody LoginRequest loginRequest) {
    public String login(@RequestBody AdminRequest adminRequest) {
        // Handle login logic here
        adminService.login(adminRequest);
//        return "redirect:/"; // Redirect to home or dashboard
        return "login successfully"; // Redirect to home or dashboard
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestBody AdminRequest adminRequest) {
        // Handle registration logic here
        adminService.register(adminRequest);

        return "redirect:/login"; // Redirect to login page after registration
    }

    // Forgot Password Page
    @GetMapping("/forgot")
    public String forgotPasswordPage() {
        return "forgot";
    }

    // Password Reset Form Submission
    @PostMapping("/reset-password")
    public String resetPasswordSubmit(@RequestBody AdminRequest adminRequest) {
        // Implement password reset logic here
        adminService.resetPassword(adminRequest);
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
