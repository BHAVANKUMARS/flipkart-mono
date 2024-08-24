package com.bhavan.controller.admin;


import com.bhavan.dto.common.AmakartRequest;
import com.bhavan.dto.customer.request.LoginRequest;
import com.bhavan.service.admin.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@Slf4j
public class AdminWebController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/login")
    public String showLoginForm(Model model) {

        model.addAttribute("loginDetails",new AmakartRequest());

        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginDetails") AmakartRequest adminRequest, Model model) {

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

        model.addAttribute("registerDetails",new AmakartRequest());

        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("registerDetails") AmakartRequest adminRequest, Model model) {
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
    public String resetPasswordSubmit(@RequestBody AmakartRequest adminRequest) {
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
    public String getProductsPage(Model model, @RequestParam Map<String, String> params) {
        // Fetch products based on search criteria from params
//        List<Product> products = productService.findProducts(params);
        log.info("params "+params);
//        model.addAttribute("products", products);
        model.addAttribute("page", "products");


        return "products"; // Thymeleaf template name
    }

    @GetMapping("/products/search")
    public String getProductSearchDetail(Model model, @RequestParam Map<String, String> params) {
        // Fetch products based on search criteria from params
//        List<Product> products = productService.findProducts(params);
        log.info("params "+params);
//        model.addAttribute("products", products);
        model.addAttribute("page", "products");


        return "products"; // Thymeleaf template name
    }

    @GetMapping("/users")
    public String users(Model model, @RequestParam Map<String, String> params){
        log.info("params "+params);
        model.addAttribute("page", "users");
        return "users";
    }

//    @RequestMapping("/users")
//    public ModelAndView viewUsers() {
//        ModelAndView mav = new ModelAndView("users");
//        List<User> users = userService.findAllUsers();
//        mav.addObject("users", users);
//        return mav;
//    }
//
//    @RequestMapping("/users/search")
//    public ModelAndView searchUsers(@RequestParam(required = false) String userId,
//                                    @RequestParam(required = false) String status,
//                                    @RequestParam(required = false) String username) {
//        ModelAndView mav = new ModelAndView("users");
//        List<User> users = userService.searchUsers(userId, status, username);
//        mav.addObject("users", users);
//        return mav;
//    }

}
