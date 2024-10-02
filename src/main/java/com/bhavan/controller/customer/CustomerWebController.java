package com.bhavan.controller.customer;

import com.bhavan.dto.common.AmakartRequest;
import com.bhavan.model.CategoryProductMap;
import com.bhavan.model.ProductDetails;
import com.bhavan.repository.categories.CategoryProductMapRepo;
import com.bhavan.repository.product.ProductDetailsRepo;
import com.bhavan.service.categories.CategoriesService;
import com.bhavan.service.customer.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/customer")
@Slf4j
public class CustomerWebController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CategoriesService categoriesService;


    @Autowired
    private CategoryProductMapRepo categoryProductMapRepo;


    @Autowired
    private ProductDetailsRepo productDetailsRepo;



    @Autowired
    private HttpSession httpSession;

    @GetMapping("/login")
    public String showLoginForm(Model model) {

        model.addAttribute("loginDetails",new AmakartRequest());

        return "cus-login";
    }

    @GetMapping("/logout")
    public String logout(Model model,HttpServletRequest servletRequest){

        httpSession = servletRequest.getSession();

        httpSession.invalidate();

        model.addAttribute("loginDetails",new AmakartRequest());

        return "redirect:/customer/login";

    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginDetails") AmakartRequest customerRequest, Model model, HttpServletRequest request) {

        // Handle login logic here
        log.info("Login Request "+customerRequest);

        String message = customerService.login(customerRequest);

        if(message.equals("Valid User")) {

            model.addAttribute("successMsg",message);

            httpSession.setAttribute("userName",customerRequest.getUserName());

            return "redirect:/customer/dashboard";
        }else {

            model.addAttribute("errMsg","Invalid Credential");

            return "cus-login";

        }

    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {

        model.addAttribute("registerDetails",new AmakartRequest());

        return "cus-register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("registerDetails") AmakartRequest adminRequest, Model model) {
        // Handle registration logic here

        log.info("Register Request "+adminRequest);

        String message = customerService.register(adminRequest);

        if(message.equals("Registered Successfully")){
            model.addAttribute("successMsg",message);
            return "cus-register";
        }else {
            model.addAttribute("errMsg",message);
            return "cus-register";
        }


    }

    // Forgot Password Page
    @GetMapping("/forgot")
    public String forgotPasswordPage() {
        return "cus-forgot";
    }

    // Password Reset Form Submission
    @PostMapping("/reset-password")
    public String resetPasswordSubmit(@RequestBody AmakartRequest adminRequest) {
        // Implement password reset logic here
        customerService.resetPassword(adminRequest);
        return "redirect:/login"; // Redirect to login page after successful password reset
    }

    // Dashboard Page
    @GetMapping("/dashboard")
    public String dashboardPage(Model model) {

        model.addAttribute("categories",categoriesService.findAllCategories());
//        model.addAttribute("userDetails",customerService.getUserDetails());
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

        return "cus-dashboard";    }

    @GetMapping("/orders")
    public String orders() {
        return "orders";
    }

    @GetMapping("/products")
    public String getProducts(@RequestParam("categoryId") Long categoryId, Model model) {
        List<ProductDetails> productDetails = new ArrayList<>();

        List<CategoryProductMap> categoryProductMaps = categoryProductMapRepo.findByCategoryId(categoryId);

        categoryProductMaps.stream().filter(c->c.getProductId()!=null)
                .forEach(
                        cp->{

                            ProductDetails productDetail = productDetailsRepo.findById(cp.getProductId()).get();
                            productDetails.add(productDetail);
                        }
                );


        model.addAttribute("products", productDetails);
        return "cus-products";
    }

    @PostMapping("/shopping/cart/details")
    @ResponseBody
    public String saveShoppingCartDetails(@RequestBody  AmakartRequest amakartRequest){

        String username = (String) httpSession.getAttribute("userName");

        amakartRequest.setUserName(username);

        return customerService.addToCart(amakartRequest);

    }

    @GetMapping("/user/profile")
    public String userProfile(Model model,HttpServletRequest servletRequest){
        HttpSession session = servletRequest.getSession();

        String username = (String) session.getAttribute("userName");

        model.addAttribute("userDetails",customerService.getUserDetails(username));

        return "cus-userprofile";
    }

}
