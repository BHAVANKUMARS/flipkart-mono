package com.bhavan.controller;


import com.bhavan.dto.admin.request.AdminRequest;
import com.bhavan.dto.customer.request.LoginRequest;
import com.bhavan.service.admin.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Slf4j
public class AdminWebController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/login")
    public String showLoginForm(Model model) {

        model.addAttribute("loginDetails",new AdminRequest());

        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginDetails") AdminRequest adminRequest, Model model) {

        // Handle login logic here
        log.info("Login Request "+adminRequest);

        String message = adminService.login(adminRequest);

        if(message.equals("Valid User")) {

            model.addAttribute("successMsg",message);

            return "redirect:/dashboard";
        }else {

            model.addAttribute("errMsg","Invalid Credential");

            return "login";

        }

    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {

        model.addAttribute("registerDetails",new AdminRequest());

        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("registerDetails") AdminRequest adminRequest, Model model) {
        // Handle registration logic here

        log.info("Register Request "+adminRequest);

        String message = adminService.register(adminRequest);

        if(message.equals("Registered Successfully")){
            model.addAttribute("successMsg",message);
            return "/register";
        }else {
            model.addAttribute("errMsg",message);
            return "/register";
        }


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
    public String dashboardPage(Model model) {
//
//        long adminCount = adminService.getAdminCount();
//        long customerCount = customerService.getCustomerCount();
//        long productCount = productService.getProductCount();
//        long orderCount = orderService.getOrderCount();

        // Add counts to the model
        model.addAttribute("adminCount", 10);
        model.addAttribute("customerCount", 20);
        model.addAttribute("productCount", 30);
        model.addAttribute("orderCount", 40);

        // Set page title
        model.addAttribute("pageName", "Dashboard");

        return "dashboard";    }

    @GetMapping("/orders")
    public String orders() {
        return "orders";
    }

    @GetMapping("/products")
    public String products() {
        return "products";
    }

    @GetMapping("/users")
    public String users(){
        
    }
}
