package com.ryhanahmedtamim.travellersservice.cotroller;



import com.ryhanahmedtamim.travellersservice.model.Status;
import com.ryhanahmedtamim.travellersservice.service.StatusPosterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @Autowired
    StatusPosterService statusPosterService;

    @GetMapping("/")
    public String index(Model model) {
        List<Status> statusList = statusPosterService.getAllPublicStatus();
        model.addAttribute("statusList", statusList);
        return "dashboard/index";
    }
}
