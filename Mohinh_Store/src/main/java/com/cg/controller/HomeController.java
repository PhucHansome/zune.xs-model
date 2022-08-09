package com.cg.controller;

import com.cg.model.User;
import com.cg.model.dto.UserDTO;
import com.cg.service.user.IUserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private IUserService userService;

    private String getPrincipal() {
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        else {
            username = principal.toString();
        }

        return username;
    }




    @GetMapping("/homecustomer")
    public ModelAndView showCustomerHomePage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/home_page_customer/home_page");
        return modelAndView;
    }

    @GetMapping("/homecustomer/product")
    public ModelAndView showCustomerPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/customer/index");
        String username = getPrincipal();
        modelAndView.addObject("user",username);
        return modelAndView;
    }

    @GetMapping("/homecustomer/order")
    public ModelAndView showOrderPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/customer/orderCustomer/order");
        String username = getPrincipal();
        modelAndView.addObject("user",username);
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView showLoginPage(){
        ModelAndView  modelAndView = new ModelAndView();
        modelAndView.setViewName("/login");
        return modelAndView;
    }
    @GetMapping("/home")
    public ModelAndView showHomePage(){
        ModelAndView  modelAndView = new ModelAndView();
        modelAndView.setViewName("/homepage_dashboard/home");
        String username = getPrincipal();
        modelAndView.addObject("user",username);
        return modelAndView;
    }

    @GetMapping("/user")
    public ModelAndView showUserPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user/list-user");
        String username = getPrincipal();
        modelAndView.addObject("user",username);
        return modelAndView;
    }

    @GetMapping("/product")
    public ModelAndView showProductPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/product/productList");
        String username = getPrincipal();
        modelAndView.addObject("user",username);
        return modelAndView;
    }

    @GetMapping("/order")
    public ModelAndView showPageOrder(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/order/orderList");
        String username = getPrincipal();
        modelAndView.addObject("user",username);
        return modelAndView;
    }
}
