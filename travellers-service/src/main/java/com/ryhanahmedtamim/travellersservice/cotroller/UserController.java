package com.ryhanahmedtamim.travellersservice.cotroller;

import com.ryhanahmedtamim.travellersservice.model.Status;
import com.ryhanahmedtamim.travellersservice.model.User;
import com.ryhanahmedtamim.travellersservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("user-register-form")
    public String registerUserForm(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "user-register-form";
    }

    @PostMapping("/add-new-user")
    public String addUser(Model model, User user){
        //User user = new User();

        String flag = userService.registerUser(user);
        model.addAttribute("user",user);
        return "user-register-form";
    }

}
