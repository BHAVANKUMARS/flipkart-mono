package com.bhavan.controller.admin;


import com.bhavan.dto.common.AmakartRequest;
import com.bhavan.dto.common.AmakartResponse;
import com.bhavan.dto.customer.request.LoginRequest;
import com.bhavan.model.OrderDetails;
import com.bhavan.model.PaymentDetails;
import com.bhavan.model.ProductDetails;
import com.bhavan.model.UserDetails;
import com.bhavan.repository.payment.PaymentRepository;
import com.bhavan.repository.product.ProductDetailsRepo;
import com.bhavan.service.admin.AdminService;
import com.bhavan.service.customer.CustomerService;
import com.bhavan.service.order.OrderService;
import com.bhavan.service.product.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value="/admin")
@Slf4j
public class AdminWebController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ProductDetailsRepo productDetailsRepo;

    @GetMapping("/logout")
    public String logout(Model model, HttpServletRequest servletRequest) {
        httpSession = servletRequest.getSession(false); // get the session, but don't create a new one if it doesn't exist

        if (httpSession != null) {
            httpSession.invalidate(); // Invalidate the session if it exists
        }

        model.addAttribute("loginDetails", new AmakartRequest()); // Add new login details model attribute

        return "redirect:/admin/login"; // Redirect to login page
    }

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

            return "redirect:/admin/dashboard";
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
        long adminCount = adminService.getTotalAdminCount();
        long customerCount = customerService.getCustomerTotalCount();
        long productCount = productService.getProductTotalCount();
        long orderCount = orderService.getOrderTotalCount().size();

        // Add counts to the model
        model.addAttribute("adminCount", adminCount);
        model.addAttribute("customerCount", customerCount);
        model.addAttribute("productCount", productCount);
        model.addAttribute("orderCount", orderCount);

        // Set page title
        model.addAttribute("pageName", "Dashboard");
        model.addAttribute("page","dashboard");

        return "dashboard";    }

    @GetMapping("/orders")
    public String orders(Model model) {
        List<OrderDetails> orderDetailsList =orderService.getAllOrderDetails();

        List<AmakartResponse> amakartResponsesList = new ArrayList<>();

        if(orderDetailsList.size() > 0){

            List <OrderDetails> orderDetailsList1 = orderDetailsList.stream().filter(orderDetails -> orderDetails.getUserId()!=null)
                    .collect(Collectors.toList());

            if(orderDetailsList1.size()> 0){

                for(OrderDetails o : orderDetailsList1){

                    AmakartResponse amakartResponse = new AmakartResponse();

                    PaymentDetails paymentDetails = paymentRepository.findById(o.getPaymentId()).get();

                    ProductDetails productDetails = productDetailsRepo.findById(o.getProductId()).get();

                    amakartResponse.setOrderDate(o.getCreatedOn());
                    amakartResponse.setOrderId(o.getOrderId());
                    amakartResponse.setProductName(productDetails.getProductName());
                    amakartResponse.setOrderStatus(o.getStatus().equalsIgnoreCase("PENDING") ?"NOT DELIVERED":"DELIVERED");
                    amakartResponse.setPaymentStatus(paymentDetails.getStatus().equals("PENDING")?"PAYMENT_PENDING":"PAYMENT_SUCCESS");
                    amakartResponse.setDeliveryOption(o.getDeliveryOptions());
                    amakartResponsesList.add(amakartResponse);
                }
            }

        }        model.addAttribute("orders",amakartResponsesList);
        return "orders";
    }

    @GetMapping("/orders/search")
    public String getOrderSearchDetail(Model model, @RequestParam Map<String, String> params) {
        List<OrderDetails> orderDetailsList = orderService.searchCriteria(params);

        List<AmakartResponse> amakartResponsesList = new ArrayList<>();

        if(orderDetailsList.size() > 0){

            List <OrderDetails> orderDetailsList1 = orderDetailsList.stream().filter(orderDetails -> orderDetails.getUserId()!=null)
                    .collect(Collectors.toList());

            if(orderDetailsList1.size()> 0){

                for(OrderDetails o : orderDetailsList1){

                    AmakartResponse amakartResponse = new AmakartResponse();

                    PaymentDetails paymentDetails = paymentRepository.findById(o.getPaymentId()).get();

                    ProductDetails productDetails = productDetailsRepo.findById(o.getProductId()).get();

                    amakartResponse.setOrderDate(o.getCreatedOn());
                    amakartResponse.setOrderId(o.getOrderId());
                    amakartResponse.setProductName(productDetails.getProductName());
                    amakartResponse.setOrderStatus(o.getStatus().equalsIgnoreCase("PENDING") ?"NOT DELIVERED":"DELIVERED");
                    amakartResponse.setPaymentStatus(paymentDetails.getStatus().equals("PENDING")?"PAYMENT_PENDING":"PAYMENT_SUCCESS");
                    amakartResponse.setDeliveryOption(o.getDeliveryOptions());


                    amakartResponsesList.add(amakartResponse);
                }
            }

        }

        model.addAttribute("orders", amakartResponsesList);
        model.addAttribute("page", "orders");

        return "orders";
    }

    @GetMapping("/products")
    public String getProductsPage(Model model, @RequestParam Map<String, String> params) {
        // Fetch products based on search criteria from params
        List<ProductDetails> products = productService.findAllActiveProducts();
        log.info("params "+params);
        model.addAttribute("products", products);
        model.addAttribute("page", "products");


        return "products"; // Thymeleaf template name
    }

    @GetMapping("/products/search")
    public String getProductSearchDetail(Model model, @RequestParam Map<String, String> params) {
        // Fetch products based on search criteria from params
        log.info("params "+params);
        List<ProductDetails> products = productService.searchCriteria(params);
        model.addAttribute("products", products);
        model.addAttribute("page", "products");


        return "products"; // Thymeleaf template name
    }

    @GetMapping("/users")
    public String users(Model model, @RequestParam Map<String, String> params){
        log.info("params "+params);
        List<UserDetails> userDetailsList =customerService.getActiveUsers();
        model.addAttribute("users",userDetailsList);
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
    @RequestMapping("/users/search")
    public String searchUsers(Model model,@RequestParam Map<String, String> params) {
        log.info("params "+params);
        List<UserDetails> userDetailsList = customerService.searchCriteria(params);
        model.addAttribute("users", userDetailsList);
        model.addAttribute("page", "users");
        return "users";
    }

}
