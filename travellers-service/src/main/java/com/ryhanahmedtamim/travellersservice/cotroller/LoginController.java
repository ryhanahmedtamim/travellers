package com.ryhanahmedtamim.travellersservice.cotroller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/")
    public String index(Model model) {


//        model.addAttribute("dashboardData", dashboardData);
//        model.addAttribute("chartData", monthNumber);

        return "dashboard/index";
    }
}
